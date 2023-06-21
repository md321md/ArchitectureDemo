package com.example.kotlinflowapplication.ui.articleSearch

import com.example.kotlinflowapplication.data.db.AppDatabase
import com.example.kotlinflowapplication.data.remote.api.ArticleService
import com.example.kotlinflowapplication.data.remote.http.ServiceCreator
import com.example.kotlinflowapplication.data.repository.ArticleCollectRepository
import com.example.kotlinflowapplication.viewModel.LatestNewsViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val articleServiceApi : ArticleService = ServiceCreator.getInstance().create(ArticleService::class.java)
val plantDao = AppDatabase.getInstance().plantDao()

@OptIn(InternalCoroutinesApi::class)
val articleModule = module {
    single { articleServiceApi }
    single { plantDao }
    single { ArticleCollectRepository() }
    viewModel {
        LatestNewsViewModel(get())
    }
}
