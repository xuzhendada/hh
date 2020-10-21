package com.hi.common.di

import android.app.Application
import com.hi.common.api.Api
import com.hi.common.data.HhRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object HiltApi {
    @Provides
    fun provideHhRepository(hhApi: Api) = HhRepository(hhApi)

    @Provides
    fun provideHhApi(retrofit: Retrofit): Api = retrofit.create(
        Api::class.java)

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