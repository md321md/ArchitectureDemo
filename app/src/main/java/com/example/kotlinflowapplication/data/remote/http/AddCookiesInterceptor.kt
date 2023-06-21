package com.example.kotlinflowapplication.data.remote.http

import okhttp3.Interceptor
import okhttp3.Response

class AddCookiesInterceptor : Interceptor{
    override fun intercept(chain: Interceptor.Chain): Response {
        val newBuilder = chain.request().newBuilder();
        newBuilder.addHeader("loginUserName_wanandroid_com","zz123")
        newBuilder.addHeader("loginUserName","zz123")
        newBuilder.addHeader("token_pass","5d9b90bcb70640183e09d1e755ead823")
        newBuilder.addHeader("token_pass_wanandroid_com","5d9b90bcb70640183e09d1e755ead823")
        newBuilder.addHeader("Cookie","loginUserName_wanandroid_com=zz123; token_pass_wanandroid_com=9256eb149c261d7430fbee8a7b02b02a; loginUserName=zz123; token_pass=9256eb149c261d7430fbee8a7b02b02a; Hm_lpvt_90501e13a75bb5eb3d067166e8d2cad8=1644890961; Hm_lvt_90501e13a75bb5eb3d067166e8d2cad8=1644481359,1644890329; JSESSIONID=0AD90569008C160652E6F89EBBE1CA70")
        return chain.proceed(newBuilder.build())
    }
}