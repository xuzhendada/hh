//package com.hi.main.vm
//
//import com.hi.common.api.HhApi
//import com.hi.common.data.HhInterfaceImpl
//import dagger.Module
//import dagger.Provides
//import dagger.hilt.InstallIn
//import dagger.hilt.components.SingletonComponent
//import javax.inject.Singleton
//import kotlin.annotation.AnnotationTarget.*
//
//
//@Module
//@InstallIn(SingletonComponent::class)
//object MainServiceModule {
//
//    @Singleton
//    @Provides
//    fun provideHhRepositoryImpl(hhApi: HhApi): HhInterfaceImpl {
//        return HhInterfaceImpl(hhApi)
//    }
//}