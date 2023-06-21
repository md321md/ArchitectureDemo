package com.example.kotlinflowapplication

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import com.example.kotlinflowapplication.ui.articleSearch.articleModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class MyApplication : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext

        startKoin{
            androidLogger()
            androidContext(this@MyApplication)
            modules(articleModule)
        }
    }

}