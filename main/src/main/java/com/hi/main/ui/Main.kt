package com.hi.main.ui

import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.constant.RouterPath
import com.hi.common.data.handleResult
import com.hi.common.ktx.createStableAdapter
import com.hi.common.ktx.navigate
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.cells.BtnCell
import com.hi.main.permission.request
import com.hi.main.vm.HiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.jetbrains.anko.toast

/**
 * Created by xuz on 2020/4/1 23:14
 */
@AndroidEntryPoint
class Main : BaseActivity() {
    private lateinit var mAdapter: StableAdapter

    private val hiltViewModel: HiltViewModel by viewModels()
    override fun layoutId() = R.layout.activity_main

    @ExperimentalCoroutinesApi
    override fun init() {
        toolbar("首页", false)
        mAdapter = createStableAdapter {
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as BtnCell
                when (itemCell.itemId()) {
                    "${R.string.sample_recycler}" -> navigate(RouterPath.SAMPLE_RECYCLER)
                    "${R.string.request_permission}" ->
                        request(
                            android.Manifest.permission.CAMERA
                        ) {
                            onGranted {
                                toast("SUCCESS")
                            }
                            onDenied {
                                toast("DENIED")
                            }
                        }
                    "${R.string.coordinator_layout}" -> navigate(RouterPath.TOP_fOLD)
                    "${R.string.page_layout}" -> navigate(RouterPath.PAGE_RECYCLER)
                    "${R.string.hilt_network}" -> {
                        hiltViewModel.getArticle().handleResult(this@Main) {
                            onSuccess {
                                toast(it.data.toString())
                            }
                            onFailure {
                                toast(it.toString())
                            }
                        }
                    }
                }
            }
        }
        recycler.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this@Main, 2)
        }
    }

    override fun viewDrawn() {
        super.viewDrawn()
        val itemCellList = mutableListOf<ItemCell>()
        itemCellList.add(BtnCell(R.string.sample_recycler))
        itemCellList.add(BtnCell(R.string.request_permission))
        itemCellList.add(BtnCell(R.string.coordinator_layout))
        itemCellList.add(BtnCell(R.string.page_layout))
        itemCellList.add(BtnCell(R.string.hilt_network))
        mAdapter.submitList(itemCellList)
    }
}
