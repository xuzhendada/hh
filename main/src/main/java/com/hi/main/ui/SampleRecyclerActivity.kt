package com.hi.main.ui

import android.app.Activity
import android.content.Intent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.constant.BundleConst
import com.hi.common.constant.RouterPath
import com.hi.common.ktx.*
import com.hi.common.widget.UniversalItemDecoration
import com.hi.main.R
import com.hi.main.cells.WanCell
import com.hi.main.databinding.ActivityRoomTestBinding
import com.hi.main.databinding.ActivitySampleRecyclerBinding
import com.hi.main.vm.WanViewModel
import kotlinx.android.synthetic.main.activity_sample_recycler.*

/**
 * 简单的recyclerView实现
 */
@Route(path = RouterPath.SAMPLE_RECYCLER)
class SampleRecyclerActivity : BaseActivity<ActivitySampleRecyclerBinding>() {
    private val mWanViewModel by lazy {
        viewModels<WanViewModel>().value
    }
    private lateinit var mAdapter: StableAdapter

    override fun init() {
        bind
        toolbar(getString(R.string.sample_recycler))
        mAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@SampleRecyclerActivity)
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as WanCell
                toast(itemCell.listResponse.toString())
            }
        }
        toast(intent.getStringExtra(BundleConst.SAMPLE_ACTIVITY) ?: "")
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
                progress_bar.visibility = View.GONE
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.sample_activity_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            R.id.ok -> {
                setResult(Activity.RESULT_OK, Intent().apply {
                    putExtra(BundleConst.SAMPLE_ACTIVITY, "FROM SAMPLE_ACTIVITY")
                })
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

    override fun viewDrawn() {
        mWanViewModel.wanListRequest()
    }
}