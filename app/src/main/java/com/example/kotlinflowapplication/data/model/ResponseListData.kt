package com.example.kotlinflowapplication.data.model

data class ResponseListData<T>(
    val data: DataListEntity<T>,
    val errorCode: Int,
    val errorMsg: String
)

data class DataListEntity<T>(
    val curPage: Int,
    val datas: List<T>,
    val offset: Int,
    val over: Boolean,
    val pageCount: Int,
    val size: Int,
    val total: Int
)