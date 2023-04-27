package com.hi.main.ui

import android.Manifest
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.hi.common.BaseActivity
import com.hi.common.ktx.intent.RequestPermissionsFactory
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.databinding.ActivityCameraxTestBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author: Travis Xu
 * @date: 2023-4-27
 * @description:
 **/
@AndroidEntryPoint
class CameraXTestActivity : BaseActivity<ActivityCameraxTestBinding>() {
    private lateinit var preview: Preview
    private lateinit var camera: Camera

    @Inject
    lateinit var permissionsFactory: RequestPermissionsFactory
    override fun init() {
        toolbar(R.string.camera_test)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.camera_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.camera -> {
            permissionsFactory.launch(arrayOf(Manifest.permission.CAMERA)) {
                onDenied {

                }
                onGranted {
                    startCamera()
                }
            }
            true
        }

        else -> true
    }

    private fun startCamera() {
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            preview = Preview.Builder().build()
            val cameraSelect = CameraSelector.Builder().requireLensFacing(CameraSelector.LENS_FACING_BACK).build()
            try {
                cameraProvider.unbind()
                camera = cameraProvider.bindToLifecycle(this, cameraSelect, preview)
                preview.setSurfaceProvider(bind.preview.surfaceProvider)
            } catch (e: Exception) {
                Log.e("cameraException", e.toString())
            }
        }, ContextCompat.getMainExecutor(this))
    }
}