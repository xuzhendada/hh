package com.hi.test1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.alibaba.android.arouter.facade.annotation.Route
import com.hi.common.constant.RouterPath

@Route(path = RouterPath.TEST_MAIN)
class Test1Activity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Text("Hello world")
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessage(){
        Text("msg")
    }
}
