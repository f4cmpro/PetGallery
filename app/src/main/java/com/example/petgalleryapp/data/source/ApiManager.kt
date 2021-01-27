package com.example.petgalleryapp.data.source

import android.os.Build
import com.example.petgalleryapp.BuildConfig
import com.example.petgalleryapp.PGApplication.Companion.context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.ConnectionPool
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.FileNotFoundException
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor() {
    private lateinit var retrofit: Retrofit

    companion object {
        private const val authorizationName = "Authorization"
        private const val authorizationValuePrefix = "OAuth2"
    }

    /***
     * okhttp
     */
    private val httpBuilder: OkHttpClient.Builder get() {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(Interceptor { chain ->
                val original = chain.request()

                //common parameter
                val url = original.url.newBuilder()
//                    .addQueryParameter("os_type", "android")
//                    .addQueryParameter("os_version", "${Build.MODEL} ${Build.VERSION.RELEASE}")
//                    .addQueryParameter("app_version", BuildConfig.VERSION_NAME)
                    .build()

                //header
                val requestBuilder = original.newBuilder()
                    .header("Content-Type", "application/json")
                    .method(original.method, original.body)
                    .url(url)

                val response = chain.proceed(requestBuilder.build())
                val responseBody = response.body
                if (response.code != 200 || responseBody == null) {
                    return@Interceptor response
                }

                val stubBody = try {
                    if (BuildConfig.FLAVOR == "mock") loadFromAssets(
                        original
                    ) else ""
                } catch (e: FileNotFoundException) { "" }

                val rawBody = if (stubBody.isNullOrEmpty()) responseBody.string() else stubBody
                return@Interceptor response.newBuilder().body(rawBody.toResponseBody(responseBody.contentType())).build()
            })
            .readTimeout(30, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            // log
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(loggingInterceptor)
        }

        return httpClient
    }

    /**
     * retrofit
     */
    fun <S> create(serviceClass: Class<S>): S{
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .add(Date::class.java, DateJsonAdapter())
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(httpBuilder.build())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()

        return retrofit.create(serviceClass)
    }

    private fun loadFromAssets(request: Request): String {
        val url = request.url.toUrl().path.substring(1)
        return context.assets.open("stub/$url.json").use {
            it.reader().readText()
        }
    }

    /**
     * OkHttpClient生成
     *
     * @return {@link OkHttpClient}
     */
    fun createDefaultOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(RequestHeaderInterceptor())
        httpClient.connectionPool(ConnectionPool(15, 3, TimeUnit.MINUTES))
        httpClient.connectTimeout(10, TimeUnit.SECONDS)
        httpClient.readTimeout(20, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            //ログ出力設定
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }

        return httpClient.build()
    }
}