package com.xz.hh.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.xz.hh.BaseActivity
import com.xz.hh.R
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.StableAdapter
import com.xz.hh.cells.WanCell
import com.xz.hh.ktx.*
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_main.*

/**
 * 简单的recyclerView实现
 */
class SampleRecyclerActivity : BaseActivity() {
    private val mWanViewModel by lazy {
        viewModels<WanViewModel>().value
    }
    private lateinit var mAdapter: StableAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sample_recycler)
        toolbar(getString(R.string.sample_recycler))
        mAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@SampleRecyclerActivity)
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as WanCell
                toast(itemCell.listResponse.toString())
            }
        }
        recycler.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(this@SampleRecyclerActivity)
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
                toast("ErrorCode:$errorCode ErrorMsg:$message")
            }
        }
    }

    override fun viewDrawn() {
        mWanViewModel.wanListRequest()
    }
}