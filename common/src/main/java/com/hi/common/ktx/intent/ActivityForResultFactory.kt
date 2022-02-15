package com.hi.common.ktx.intent

import android.app.Activity
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import javax.inject.Inject

class ActivityForResultFactory @Inject constructor(activity: FragmentActivity) {
    private var activityForResultDsl: ActivityForResultDsl? = null

    private val launcher: ActivityResultLauncher<Intent> =
        activity.registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            when (it.resultCode) {
                Activity.RESULT_OK -> activityForResultDsl?.doOk(it.data)
                Activity.RESULT_CANCELED -> activityForResultDsl?.doCancel()
            }
        }

    fun launch(intent: Intent, dsl: ActivityForResultDsl.() -> Unit) {
        activityForResultDsl = ActivityForResultDsl().apply(dsl)
        launcher.launch(intent)
    }
}