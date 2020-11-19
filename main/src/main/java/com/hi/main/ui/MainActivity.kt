package com.hi.main.ui

import android.content.Intent
import androidx.activity.viewModels
import androidx.core.os.bundleOf
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.GridLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.constant.BundleConst
import com.hi.common.data.handleResult
import com.hi.common.ktx.createStableAdapter
import com.hi.common.ktx.intent.ActivityForResultFactory
import com.hi.common.ktx.intent.RequestPermissionsFactory
import com.hi.common.ktx.intent.intentOf
import com.hi.common.ktx.intent.startActivity
import com.hi.common.ktx.toast
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.cells.BtnCell
import com.hi.main.vm.HiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * Created by xuz on 2020/4/1 23:14
 */
@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private lateinit var mAdapter: StableAdapter

    private val hiltViewModel: HiltViewModel by viewModels()

    @Inject
    lateinit var permissionsFactory: RequestPermissionsFactory

    @Inject
    lateinit var activityForResultFactory: ActivityForResultFactory

    private val liveData = MutableLiveData<Int>()

    override fun layoutId() = R.layout.activity_main

    override fun init() {
        toolbar("首页", false)
        thread {
            liveData.postValue(10)
        }
        mAdapter = createStableAdapter {
            onSimpleCallback { position ->
                val itemCell = mAdapter.currentList()[position] as BtnCell
                when (itemCell.itemId()) {
                    getString(R.string.sample_recycler) ->
                        activityForResultFactory.launch(
                            intentOf<SampleRecyclerActivity>(
                                bundleOf(BundleConst.SAMPLE_ACTIVITY to "FROM MAIN")
                            )
                        ) {
                            onOk {
                                toast(it?.getStringExtra(BundleConst.SAMPLE_ACTIVITY) ?: "")
                            }
                            onCancel {

                            }
                        }
                    getString(R.string.request_permission) -> {
                        permissionsFactory.launch(
                            arrayOf(
                                android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            )
                        ) {
                            onGranted {
                                toast("申请成功")
                            }
                            onDenied {
                                toast("申请失败")
                            }
                        }
                    }
                    getString(R.string.coordinator_layout) -> startActivity<TopFoldActivity>()
                    getString(R.string.page_layout) -> startActivity<PageRecyclerActivity>()
                    getString(R.string.hilt_network) -> {
                        hiltViewModel.getArticle().handleResult(this@MainActivity) {
                            onSuccess {
                                toast(it.data.toString())
                            }
                            onFailure {
                                toast(it.toString())
                            }
                        }
                    }
                    getString(R.string.smart_refresh_layout) -> {
                        val intent = Intent(this@MainActivity, SmartRefreshActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.room_test) -> startActivity<RoomTestActivity>()
                    getString(R.string.select_img) -> startActivity<SelectImgActivity>()
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
        itemCellList.add(BtnCell(getString(R.string.coordinator_layout)))
        itemCellList.add(BtnCell(getString(R.string.page_layout)))
        itemCellList.add(BtnCell(getString(R.string.hilt_network)))
        itemCellList.add(BtnCell(getString(R.string.smart_refresh_layout)))
        itemCellList.add(BtnCell(getString(R.string.room_test)))
        itemCellList.add(BtnCell(getString(R.string.select_img)))
        mAdapter.submitList(itemCellList)
    }
}
