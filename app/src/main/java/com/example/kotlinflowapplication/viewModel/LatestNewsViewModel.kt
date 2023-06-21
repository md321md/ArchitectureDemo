package com.example.kotlinflowapplication.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinflowapplication.data.model.ArticleCollect
import com.example.kotlinflowapplication.data.repository.ArticleCollectRepository
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

@InternalCoroutinesApi
class LatestNewsViewModel(
    private val articleCollectRepository: ArticleCollectRepository,
) : ViewModel() {
    val listData = mutableListOf<ArticleCollect>()
    fun getList(page: Int, callBack: (list : List<ArticleCollect>) -> Unit) {
        viewModelScope.launch {
            val list = articleCollectRepository.getCollectList(page)
            listData.addAll(list.data.datas)
            callBack.invoke(list.data.datas)
        }
    }
}
