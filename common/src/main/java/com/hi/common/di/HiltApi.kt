package com.hi.common.di

import android.app.Application
import com.hi.common.api.HhApi
import com.hi.common.data.HhInterfaceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HiltApi {
    @Provides
    @Singleton
    fun provideHhRepository(hhApi: HhApi) = HhInterfaceImpl(hhApi)

    @Provides
    fun provideHhApi(retrofit: Retrofit): HhApi = retrofit.create(
        HhApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder().baseUrl("https://wanandroid.com").client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create()).build()

    @Provides
    fun provideOkHttpClient(application: Application) = OkHttpClient.Builder()
        .connectTimeout(1000, TimeUnit.MILLISECONDS)
        .readTimeout(1000, TimeUnit.MILLISECONDS)
        .cache(Cache(application.cacheDir, 104857600L))
        .addInterceptor(HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        })
        .build()
}