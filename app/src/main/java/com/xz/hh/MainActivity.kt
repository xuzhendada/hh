package com.xz.hh

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xz.hh.adapter.ItemCell
import com.xz.hh.adapter.StableAdapter
import com.xz.hh.cells.BtnCell
import com.xz.hh.cells.WanCell
import com.xz.hh.ktx.*
import com.xz.hh.permission.request
import com.xz.hh.ui.PageRecyclerActivity
import com.xz.hh.ui.SampleRecyclerActivity
import com.xz.hh.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.browse
import org.jetbrains.anko.share
import org.jetbrains.anko.startActivity

/**
 * Created by xuz on 2020/4/1 23:14
 */
class MainActivity : BaseActivity() {
    private lateinit var mAdapter: StableAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mAdapter = createStableAdapter {
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as BtnCell
                when (itemCell.itemId()) {
                    getString(R.string.sample_recycler) -> {
                        startActivity<SampleRecyclerActivity>()
                    }
                    getString(R.string.request_permission) -> {
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
                    }
                }
            }
        }
        recycler.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    override fun viewDrawn() {
        super.viewDrawn()
        val itemCellList = mutableListOf<ItemCell>()
        itemCellList.add(BtnCell(getString(R.string.sample_recycler)))
        itemCellList.add(BtnCell(getString(R.string.request_permission)))
        mAdapter.submitList(itemCellList)
    }
}
