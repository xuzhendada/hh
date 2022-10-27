package com.xz.hh.permission

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment

class PermissionFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }

    fun requestPermissionsByFragment(permissions: Array<String>, requestCode: Int) {
        requestPermissions(permissions, requestCode)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        val neverAskPermissions = mutableListOf<String>()
        val deniedPermissions = mutableListOf<String>()
        val grantedPermissions = mutableListOf<String>()
        permissions.forEachIndexed { index, permission ->
            if (grantResults[index] == PackageManager.PERMISSION_DENIED) {
                if (shouldShowRequestPermissionRationale(permission)) {
                    deniedPermissions.add(permission)
                } else {
                    neverAskPermissions.add(permission)
                }
            } else {
                grantedPermissions.add(permission)
            }
        }
        val permissionsCallback = PermissionsMap.get(requestCode)
        if (deniedPermissions.isNotEmpty()) {
            // denied
            permissionsCallback?.denied(deniedPermissions)
        }
        if (neverAskPermissions.isNotEmpty()) {
            // never ask
            permissionsCallback?.neverAskAgain(neverAskPermissions)
        }
        if (deniedPermissions.isEmpty() && neverAskPermissions.isEmpty()) {
            // granted
            permissionsCallback?.granted()
        }
    }
}