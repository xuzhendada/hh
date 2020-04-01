package com.xz.hh

import android.widget.ImageView
import androidx.fragment.app.FragmentActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions

/**
 * Created by xuz on 2020/3/27
 * function:Glide Util
 */
class ImageLoader(activity: FragmentActivity) {
    private val mRequestManger: RequestManager = Glide.with(activity)
    private val mRequestOptions by lazy {
        RequestOptions()
    }

    //輸出圆形圖片
    fun displayCircleCrop(
        view: ImageView,
        url: String?,
        placeholder: Int = 0,//佔位符
        error: Int = 0//錯誤圖片
    ) {
        mRequestManger.load(url).apply(
            mRequestOptions
                .autoClone()
                .placeholder(placeholder)
                .error(error)
                .transform(CircleCrop())
        ).into(view)
    }
}

fun FragmentActivity.imageLoader(): ImageLoader = ImageLoader(this)