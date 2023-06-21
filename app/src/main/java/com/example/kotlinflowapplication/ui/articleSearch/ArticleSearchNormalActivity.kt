package com.example.kotlinflowapplication.ui.articleSearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import com.example.kotlinflowapplication.databinding.ArticleSearchNormalListBinding
import com.example.kotlinflowapplication.view.CustomRecycleView
import com.example.kotlinflowapplication.view.CustomRecycleView.Companion.NETWORK_PAGE_SIZE
import com.example.kotlinflowapplication.view.ListCallBack
import com.example.kotlinflowapplication.view.RefreshCallBack
import com.example.kotlinflowapplication.viewModel.LatestNewsViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.ext.android.viewModel

@InternalCoroutinesApi
class ArticleSearchNormalActivity : AppCompatActivity() {

    companion object {
        fun startArticleSearchNormalActivity(context: Context) {
            context.startActivity(Intent(context, ArticleSearchNormalActivity::class.java))
        }
    }

    private val viewModel: LatestNewsViewModel by viewModel()

    private lateinit var binding: ArticleSearchNormalListBinding
    private lateinit var recycleView: CustomRecycleView

    private lateinit var collectAdapter: ArticleSearchNormalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ArticleSearchNormalListBinding.inflate(LayoutInflater.from(this))

        setContentView(binding.root)

        recycleView = binding.customRecycleView

        collectAdapter = ArticleSearchNormalAdapter()

        recycleView.setAdapter(collectAdapter)

        viewModel.getList(0) {
            recycleView.submitList(viewModel.listData)
        }

        // 加载更多
        recycleView.loadMore(object : ListCallBack {
            override fun loadMore(page: Int, callBack: (isLoadMore: Boolean) -> Unit) {
                viewModel.getList(page) {
                    recycleView.submitList(viewModel.listData)
                    callBack.invoke(it.size == NETWORK_PAGE_SIZE)
                }
            }
        })

        // 下拉刷新
        recycleView.refresh(object : RefreshCallBack {
            override fun refresh(callBack: () -> Unit) {
                viewModel.listData.clear()
                viewModel.getList(0) {
                    recycleView.submitList(viewModel.listData)
                    callBack.invoke()
                }
            }

        })
    }
}

