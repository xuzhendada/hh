package com.xz.hh

import com.xz.hh.api.Api
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitUtil private constructor() {

    companion object {
        private val mRetrofit: Retrofit by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            val mClientBuilder =
                OkHttpClient.Builder()
                    .connectTimeout(1000, TimeUnit.MILLISECONDS)
                    .readTimeout(1000, TimeUnit.MILLISECONDS)
                    .build()

            Retrofit.Builder()
                .baseUrl("https://wanandroid.com")
                .addConverterFactory(MoshiConverterFactory.create())
                .client(mClientBuilder).build()
        }
        val mApi: Api by lazy {
            mRetrofit.create(Api::class.java)
        }
    }
}