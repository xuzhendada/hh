package com.xz.hh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.xz.hh.ktx.applyResponse
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by xuz on 2020/4/1 23:14
 */
class MainActivity : AppCompatActivity() {

    private val mWanViewModel by lazy {
        viewModels<WanViewModel>().value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        click.setOnClickListener {
            mWanViewModel.wanListRequest()
        }
        mWanViewModel.subscribeList().applyResponse(this) {
            onSuccess {
                Log.e("TAG", "$it")
            }
            onFail { errorCode, message ->
                Log.e("TAG", "ErrorCode:$errorCode ErrorMsg:$message")
            }
        }

    }
}
