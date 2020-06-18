package com.xz.hh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.StableAdapter
import com.xz.hh.cells.WanCell
import com.xz.hh.ktx.ImageLoader
import com.xz.hh.ktx.applyResponse
import com.xz.hh.ktx.createStableAdapter
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by xuz on 2020/4/1 23:14
 */
class MainActivity : BaseActivity() {

    private val mWanViewModel by lazy {
        viewModels<WanViewModel>().value
    }
    private lateinit var mAdapter: StableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@MainActivity)
        }
        recycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
        mWanViewModel.subscribeList().applyResponse(this) {
            onSuccess {
                val itemCellList = mutableListOf<ItemCell>()
                it.forEach { res ->
                    itemCellList.add(WanCell(res))
                }
                mAdapter.submitList(itemCellList)
            }
            onFail { errorCode, message ->
                Log.e("TAG", "ErrorCode:$errorCode ErrorMsg:$message")
            }
        }
    }

    override fun viewDrawn() {
        mWanViewModel.wanListRequest()
    }
}
