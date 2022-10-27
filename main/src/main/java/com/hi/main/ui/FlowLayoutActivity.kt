package com.hi.main.ui

import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.ktx.createStableAdapter
import com.hi.common.ktx.toolbar
import com.hi.common.room.STUDENT_DATA
import com.hi.common.widget.CardLayoutManager
import com.hi.main.R
import com.hi.main.cells.FlowLayoutCell
import com.hi.main.databinding.ActivityFlowLayoutBinding

/**
 * @author wbxuzhen
 * date : 2021/5/28 9:57
 * description : 可拖拽的流式布局
 */
class FlowLayoutActivity : BaseActivity<ActivityFlowLayoutBinding>() {
    private lateinit var stableAdapter: StableAdapter
    override fun init() {
        toolbar(R.string.flow_layout2)
        stableAdapter = createStableAdapter { }
        bind.recycler.apply {
            layoutManager = CardLayoutManager()
            adapter = stableAdapter
        }
        stableAdapter.submitList(getCellList())
    }

    /**
     * 拼装cellList
     */
    private fun getCellList(): MutableList<ItemCell> {
        val itemCellList = mutableListOf<ItemCell>()
        STUDENT_DATA.forEach {
            itemCellList.add(FlowLayoutCell(it))
        }
        return itemCellList
    }
}