package com.hi.main.ui

import com.hi.common.BaseActivity
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.databinding.ActivityCameraxTestBinding

class CameraXTestActivity : BaseActivity<ActivityCameraxTestBinding>() {
    override fun init() {
        toolbar(R.string.camera_test)
    }
}