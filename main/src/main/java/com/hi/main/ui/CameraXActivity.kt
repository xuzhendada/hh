package com.hi.main.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hi.main.databinding.ActivityCameraBinding

/**
 * Creatd by wbxuzhen on 2021/3/25 15:08
 * description:
 */
class CameraXActivity : AppCompatActivity() {
    private val bind by lazy {
        ActivityCameraBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
    }
}