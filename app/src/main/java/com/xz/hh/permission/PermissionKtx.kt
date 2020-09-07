package com.xz.hh.permission

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity

const val TAG = "PermissionFragment"

/**
 * 权限请求
 */
fun FragmentActivity.request(
    vararg permissions: String,
    dsl: PermissionsCallback.() -> Unit
) {
    val permissionsCallback = PermissionsCallback().apply(dsl)
    val requestCode = PermissionsMap.put(permissionsCallback)

    val needRequestPermissions = permissions.filter { !isGranted(it) }

    if (needRequestPermissions.isEmpty())
        permissionsCallback.granted()
    else
        getPermissionFragment(this).requestPermissionsByFragment(
            needRequestPermissions.toTypedArray(),
            requestCode
        )
}

private fun getPermissionFragment(activity: FragmentActivity): PermissionFragment {
    var fragment = activity.supportFragmentManager.findFragmentByTag(TAG)
    if (fragment == null) {
        fragment = PermissionFragment()
        activity.supportFragmentManager.beginTransaction().add(
            fragment,
            TAG
        ).commitNow()
    }
    return fragment as PermissionFragment
}

@SuppressLint("ObsoleteSdkInt")
fun FragmentActivity.isGranted(permission: String) =
    Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED