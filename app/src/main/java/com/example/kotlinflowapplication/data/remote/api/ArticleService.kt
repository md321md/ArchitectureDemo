package com.example.kotlinflowapplication.data.remote.api

import com.example.kotlinflowapplication.data.model.ArticleCollect
import com.example.kotlinflowapplication.data.model.ResponseListData
import retrofit2.http.*

interface ArticleService {
    // 查询个人收藏
    @GET("lg/collect/list/{page}/json")
    suspend fun collectListPage(
        @Path("page") page: Int,@Query("page_size") page_size: Int
    ): ResponseListData<ArticleCollect>
}