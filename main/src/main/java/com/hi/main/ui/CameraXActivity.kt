package com.hi.main.ui

import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import com.hi.common.BaseActivity
import com.hi.common.ktx.debounceClick
import com.hi.main.R
import com.hi.main.databinding.ActivityCameraBinding
import kotlinx.android.synthetic.main.activity_camera.*
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * Creatd by wbxuzhen on 2021/3/25 15:08
 * description:CameraX
 */
class CameraXActivity : BaseActivity<ActivityCameraBinding>() {

    private var imageCapture: ImageCapture? = null
    private lateinit var file: File
    private lateinit var cameraExecutor: ExecutorService

    override fun init() {
        startCamera()
        mbTakePhoto.debounceClick {
            takePhoto()
        }
        file = getOutPutDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun startCamera() {
        // Used to bind the lifecycle of cameras to the lifecycle owner
        val cameraProviderFuture = ProcessCameraProvider.getInstance(this)
        cameraProviderFuture.addListener(Runnable {
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview =
                Preview.Builder().build().also { it.setSurfaceProvider(pvPreView.surfaceProvider) }
            val camera = CameraSelector.DEFAULT_BACK_CAMERA
            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(this, camera, preview)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }, ContextCompat.getMainExecutor(this))
    }

    private fun takePhoto() {

    }

    private fun getOutPutDirectory(): File {
        val mediaDir = externalCacheDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}