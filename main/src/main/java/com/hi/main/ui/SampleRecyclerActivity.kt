package com.hi.main.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.constant.RouterPath
import com.hi.common.ktx.*
import com.hi.common.widget.UniversalItemDecoration
import com.hi.main.R
import com.hi.main.cells.WanCell
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_sample_recycler.*

/**
 * 简单的recyclerView实现
 */
@Route(path = RouterPath.SAMPLE_RECYCLER)
class SampleRecyclerActivity : BaseActivity() {
    private val mWanViewModel by lazy {
        viewModels<WanViewModel>().value
    }
    private lateinit var mAdapter: StableAdapter

    override fun layoutId() = R.layout.activity_sample_recycler

    override fun init() {
        toolbar(getString(R.string.sample_recycler))
        mAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@SampleRecyclerActivity)
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as WanCell
                toast(itemCell.listResponse.toString())
            }
        }
        recycler.apply {
            addItemDecoration(
                UniversalItemDecoration(
                    this@SampleRecyclerActivity,
                    dimen(R.dimen.margin_left),
                    dimen(R.dimen.margin_right)
                )
            )
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