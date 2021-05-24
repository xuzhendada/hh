package com.hi.main.ui

import androidx.recyclerview.widget.LinearLayoutManager
import com.hi.common.BaseActivity
import com.hi.common.adapter.ItemCell
import com.hi.common.adapter.StableAdapter
import com.hi.common.ktx.ImageLoader
import com.hi.common.ktx.createStableAdapter
import com.hi.main.cells.TimeLineCell
import com.hi.main.databinding.ActivityTimeLineBinding
import kotlinx.android.synthetic.main.activity_time_line.*

/**
 * @author wbxuzhen
 * date : 2021/5/24 12:06
 * description :时间线
 */
class TimeLineActivity : BaseActivity<ActivityTimeLineBinding>() {
    private lateinit var stableAdapter: StableAdapter
    private var selectPosition = 0//记录当前选择的
    override fun init() {
        stableAdapter = createStableAdapter {
            imageLoader = ImageLoader(this@TimeLineActivity)
            onTimeLineCallback { position, type ->
                when (type) {
                    0 -> {
                        if (selectPosition != position) {
                            val cell = stableAdapter.currentList()
                            (cell[position] as TimeLineCell).check = true
                            (cell[selectPosition] as TimeLineCell).check = false
                            stableAdapter.submitList(cell) {
                                stableAdapter.notifyDataSetChanged()
                                selectPosition = position
                            }
//                            stableAdapter.notifyItemChanged(position, true)
//                            stableAdapter.notifyItemChanged(selectPosition, false)
//                            selectPosition = position
                        }
                    }
                    1 -> {
                        //这里写点击图片的事件
                    }
                }


            }
        }
        recycler.apply {
            layoutManager = LinearLayoutManager(this@TimeLineActivity)
            adapter = stableAdapter
        }
        val cellList = mutableListOf<ItemCell>()
        cellList.add(
            TimeLineCell(
                true,
                "罗斯福号航母参与演习1",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624426178&t=bd7e65c5270fdde46bff33afdedeb47e",
                "2018年", false
            )
        )
        cellList.add(
            TimeLineCell(
                false,
                "罗斯福号航母参与演习2",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624426178&t=bd7e65c5270fdde46bff33afdedeb47e",
                "2018年", false
            )
        )
        cellList.add(
            TimeLineCell(
                false,
                "罗斯福号航母参与演习3",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624426178&t=bd7e65c5270fdde46bff33afdedeb47e",
                "2018年", false
            )
        )
        cellList.add(
            TimeLineCell(
                false,
                "罗斯福号航母参与演习4",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624426178&t=bd7e65c5270fdde46bff33afdedeb47e",
                "2018年", false
            )
        )
        cellList.add(
            TimeLineCell(
                false,
                "罗斯福号航母参与演习5",
                "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1624426178&t=bd7e65c5270fdde46bff33afdedeb47e",
                "2018年", true
            )
        )
        stableAdapter.submitList(cellList)
    }
}