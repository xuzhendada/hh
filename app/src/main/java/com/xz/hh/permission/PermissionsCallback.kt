package com.xz.hh.permission

class PermissionsCallback {
    private var granted: () -> Unit = {}
    private var denied: (permissions: MutableList<String>) -> Unit = {}
    private var neverAskAgain: (permissions: MutableList<String>) -> Unit = {}

    fun onGranted(block: () -> Unit) {
        granted = block
    }

    fun onDenied(block: (permissions: MutableList<String>) -> Unit) {
        denied = block
    }

    fun onNeverAskAgain(block: (permissions: MutableList<String>) -> Unit) {
        neverAskAgain = block
    }

    fun granted() {
        granted.invoke()
    }

    fun denied(permissions: MutableList<String>) {
        denied.invoke(permissions)
    }

    fun neverAskAgain(permissions: MutableList<String>) {
        neverAskAgain.invoke(permissions)
    }
}