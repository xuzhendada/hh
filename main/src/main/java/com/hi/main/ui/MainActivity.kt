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
import com.hi.common.constant.RouterPath
import com.hi.common.data.HhResult
import com.hi.common.data.WanResponse
import com.hi.common.data.response.Banner
import com.hi.common.ktx.createStableAdapter
import com.hi.common.ktx.intent.ActivityForResultFactory
import com.hi.common.ktx.intent.RequestPermissionsFactory
import com.hi.common.ktx.intent.intentOf
import com.hi.common.ktx.intent.startActivity
import com.hi.common.ktx.navigate
import com.hi.common.ktx.toast
import com.hi.common.ktx.toolbar
import com.hi.main.R
import com.hi.main.cells.BtnCell
import com.hi.main.databinding.ActivityMainBinding
import com.hi.main.vm.HhHiltViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.concurrent.thread

/**
 * Created by xuz on 2020/4/1 23:14
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {
    private lateinit var mAdapter: StableAdapter

    private val hiltViewModel: HhHiltViewModel by viewModels()

    @Inject
    lateinit var permissionsFactory: RequestPermissionsFactory

    @Inject
    lateinit var activityForResultFactory: ActivityForResultFactory


    private val liveData = MutableLiveData<Int>()


    override fun init() {
        toolbar("首页", false)
        thread {
            liveData.postValue(10)
        }
        subscribeBanner()
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
                                toast("onGranted")
                            }
                            onDenied {
                                toast("onDenied")
                            }
                        }
                    }
                    getString(R.string.coordinator_layout) -> startActivity<TopFoldActivity>()
                    getString(R.string.page_layout) -> startActivity<PageRecyclerActivity>()
                    getString(R.string.hilt_network) -> hiltViewModel.getBanner()
                    getString(R.string.smart_refresh_layout) -> {
                        val intent = Intent(this@MainActivity, SmartRefreshActivity::class.java)
                        startActivity(intent)
                    }
                    getString(R.string.room_test) -> startActivity<RoomTestActivity>()
                    getString(R.string.select_img) -> startActivity<SelectImgActivity>()
                    getString(R.string.flow_layout) -> startActivity<FlowLayoutTestActivity>()
                    /*测试路由的话，打开test1模块,gradle.properties(project)->test1_dev=1*/
                    getString(R.string.a_route) -> navigate(RouterPath.TEST_MAIN)
                    getString(R.string.time_line) -> startActivity<TimeLineActivity>()
                    getString(R.string.flow_layout2) -> startActivity<FlowLayoutActivity>()
                }
            }
        }
        bind.recycler.apply {
            adapter = mAdapter
            layoutManager = GridLayoutManager(this@MainActivity, 2)
        }
    }

    private fun subscribeBanner() {
        hiltViewModel.bannerData.observe(this) {
            when (it) {
                is HhResult.Success<WanResponse<List<Banner>>> -> {
                    toast(it.value.data.toString())
                }
                is HhResult.Failure -> {
                    toast(it.throwable.toString())
                }
                else -> {}
            }
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
        itemCellList.add(BtnCell(getString(R.string.flow_layout)))
        itemCellList.add(BtnCell(getString(R.string.a_route)))
        itemCellList.add(BtnCell(getString(R.string.flow_layout2)))
        itemCellList.add(BtnCell(getString(R.string.time_line)))
        mAdapter.submitList(itemCellList)
    }
}
