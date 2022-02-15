package com.xz.hh

import android.app.Application
import android.content.ComponentCallbacks2
import com.alibaba.android.arouter.launcher.ARouter
import com.bumptech.glide.Glide
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HhApp : Application() {
    override fun onCreate() {
        super.onCreate()
        ARouter.openLog()
        ARouter.openDebug()
        ARouter.init(this)
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        //TRIM_MEMORY_UI_HIDDEN这个值代表了当前应用的UI已不再可见。通过它，我们就可以认为应用进入了后台。
        if (level == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            Glide.get(this).clearMemory()
        }
        Glide.get(this).trimMemory(level)
    }

    override fun onLowMemory() {
        super.onLowMemory()
        //內存低的時候清空緩存
        Glide.get(this).clearMemory()
    }
}