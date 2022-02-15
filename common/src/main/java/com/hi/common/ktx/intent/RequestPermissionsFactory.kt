package com.hi.common.ktx.intent

import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Inject

/**
 * 动态申请权限
 */
class RequestPermissionsFactory @Inject constructor(activity: FragmentActivity) {

    private var permissionsForResultDsl: PermissionsForResultDsl? = null

    private val launcher: ActivityResultLauncher<Array<String>> =
        activity.registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
            val isGranted = it.entries.none { item -> !item.value }
            if (isGranted)
                permissionsForResultDsl?.doGranted()
            else
                permissionsForResultDsl?.doDenied()
        }

    fun launch(permissions: Array<String>, dsl: PermissionsForResultDsl.() -> Unit) {
        permissionsForResultDsl = PermissionsForResultDsl().apply(dsl)
        launcher.launch(permissions)
    }
}