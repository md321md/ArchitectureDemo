package com.example.kotlinflowapplication.data.repository

import com.example.kotlinflowapplication.ui.articleSearch.articleServiceApi
import com.example.kotlinflowapplication.ui.articleSearch.plantDao
import com.example.kotlinflowapplication.view.CustomRecycleView.Companion.NETWORK_PAGE_SIZE
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ArticleCollectRepository {
    // 正常分页
    suspend fun getCollectList(page: Int) = withContext(Dispatchers.IO) {
        val collectList = articleServiceApi.collectListPage(page,NETWORK_PAGE_SIZE)
        plantDao.insertAllArticleCollects(collectList.data.datas)
        collectList
    }
}
