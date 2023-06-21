package com.example.kotlinflowapplication.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.scwang.smartrefresh.layout.listener.OnRefreshListener


class CustomRecycleView : FrameLayout {

    companion object {
        const val NETWORK_PAGE_SIZE = 10
    }

    private var shouldLoadMore = true

    private var smartRefreshLayout: SmartRefreshLayout = SmartRefreshLayout(context)

    private var recycleView: RecyclerView = RecyclerView(context)
    private var layoutManager: LinearLayoutManager = LinearLayoutManager(context)

    constructor(context: Context) : this(context, null)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context,
        attrs,
        defStyleAttr) {

        setupRefresh()
        setupRecycleView()

        smartRefreshLayout.addView(recycleView)
        addView(smartRefreshLayout)


    }

    private fun setupRecycleView() {
        val params = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        recycleView.layoutParams = params
        recycleView.layoutManager = layoutManager

        recycleView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val lastVisibleItem = layoutManager.findLastCompletelyVisibleItemPosition()
                if ((lastVisibleItem >= layoutManager.itemCount - 1) && shouldLoadMore) {
                    val count: Int? = recycleView.adapter?.itemCount
                    val page = count?.div(NETWORK_PAGE_SIZE)
                            ?.let { Math.ceil(it.toDouble()).toInt() }
                    listCallBack.loadMore(page ?: 0) {
                        shouldLoadMore = it
                    }
                }
            }
        })
    }

    private fun setupRefresh() {
        val paramsSmartRefreshLayout = LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        smartRefreshLayout.layoutParams = paramsSmartRefreshLayout
        smartRefreshLayout.setRefreshHeader(ClassicsHeader(context))
        smartRefreshLayout.setOnRefreshListener(OnRefreshListener { refreshlayout ->
            shouldLoadMore = true
            refreshCallBack.refresh {
                refreshlayout.finishRefresh(true /*,false*/) //传入false表示刷新失败
            }
        })
    }

    private lateinit var mBaseListAdapter: BaseListAdapter<*>

    fun <T> setAdapter(adapter: BaseListAdapter<T>) {
        mBaseListAdapter = adapter
        recycleView.adapter = mBaseListAdapter
    }

    private fun <T> getAdapter() : BaseListAdapter<T> = recycleView.adapter as BaseListAdapter<T>

    fun <T> submitList(list : List<T>) {
        val baseListAdapter = recycleView.adapter as BaseListAdapter<T>
        baseListAdapter.submitList(list)
    }

    fun loadMore(ListCallBack : ListCallBack){
        listCallBack = ListCallBack
    }

    fun refresh(RefreshCallBack : RefreshCallBack){
        refreshCallBack = RefreshCallBack
    }

    private lateinit var listCallBack : ListCallBack
    private lateinit var refreshCallBack : RefreshCallBack

}

interface ListCallBack {
    fun loadMore(page : Int,callBack : (isLoadMore: Boolean) -> Unit)
}
interface RefreshCallBack {
    fun refresh(callBack : () -> Unit)
}