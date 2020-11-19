package com.hi.main.ui

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.ktx.*
import com.hi.common.ktx.intent.RequestPermissionsFactory
import com.hi.main.R
import com.hi.main.cells.SelectImgCell
import com.zhihu.matisse.Matisse
import com.zhihu.matisse.MimeType
import com.zhihu.matisse.engine.impl.GlideEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_select_img.*
import javax.inject.Inject

/**
 *  Created by wbxuzhen on 2020/11/19 13:45.
 *  des:图片选择
 */
@AndroidEntryPoint
class SelectImgActivity : BaseActivity() {

    private var imgList = MutableLiveData<List<Uri>>()
    private val img = mutableListOf<Uri>()

    @Inject
    lateinit var requestPermissionsFactory: RequestPermissionsFactory

    private lateinit var stableAdapter: StableAdapter
    override fun layoutId() = R.layout.activity_select_img

    override fun init() {
        toolbar(getString(R.string.select_img))
        stableAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@SelectImgActivity)
        }
        recycler.apply {
            layoutManager = GridLayoutManager(this@SelectImgActivity, 4)
            adapter = stableAdapter
        }
        imgList.observe(this, {
            val itemList = mutableListOf<ItemCell>()
            it.forEach { uri ->
                itemList.add(SelectImgCell(uri))
            }
            stableAdapter.submitList(itemList)
        })
    }

    /**
     * 知乎图片选择
     */
    private fun addImg() {
        Matisse.from(this)
            .choose(MimeType.of(MimeType.JPEG, MimeType.PNG, MimeType.WEBP))
            .countable(true)
            .maxSelectable(20 - (imgList.value?.size ?: 0))
            .gridExpectedSize(dimen(R.dimen.grid_expected_size))
            .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
            .thumbnailScale(0.85f)
            .imageEngine(GlideEngine())
            .showPreview(false)
            .forResult(100)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.select_img_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.clear_all -> {
            img.clear()
            imgList.postValue(img)
            true
        }
        R.id.select_img -> {
            requestPermissionsFactory.launch(
                arrayOf(
                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.CAMERA
                )
            ) {
                onGranted {
                    if (img.size >= 20) toast("选择的图片已经20张")
                    else addImg()
                }
                onDenied {

                }
            }
            true
        }

        else -> super.onOptionsItemSelected(item)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                100 -> {
                    img.addAll(Matisse.obtainResult(data))
                    imgList.postValue(img)
                }
            }
        }
    }

}