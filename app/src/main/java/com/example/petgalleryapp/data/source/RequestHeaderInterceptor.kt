package com.example.petgalleryapp.data.source

import okhttp3.Interceptor
import okhttp3.Response

class RequestHeaderInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()
        val request = original.newBuilder()
            .method(original.method, original.body)
            .build()

        return chain.proceed(request)
    }
}