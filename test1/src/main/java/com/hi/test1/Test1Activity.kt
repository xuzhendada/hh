package com.hi.test1

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
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
                Scaffold(topBar = {
                    TopAppBar() {

                    }
                }, bottomBar = {
                    BottomAppBar() {

                    }
                },
                ) { it ->
                    it.calculateBottomPadding()
                    it.calculateTopPadding()
                }
                Text("Hello world")
                Column {
                    TextButton(onClick = { /*TODO*/ }) {

                    }
                    Text(text = "hha")
                    Button(onClick = { click() }) {
                        Text(text = "hh")
                    }
                }
            }
        }
    }

    @Preview
    @Composable
    fun PreviewMessage() {
        Text("msg")
    }

    private fun click() {
        Log.e("TAG", "hhh")
    }
}
