package com.hi.common.widget

import android.graphics.Rect
import android.util.SparseArray
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView


/**
 * @author wbxuzhen
 * date : 2021/5/27 15:11
 * description : 自定义layoutManager 实现流式布局
 */
class CardLayoutManager : RecyclerView.LayoutManager() {
    private var totalHeight = 0
    private val allItem by lazy {
        SparseArray<Rect>()
    }
    private var verticalScrollOffset = 0

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        super.onLayoutChildren(recycler, state)
        if (itemCount <= 0) return
        if (state?.isPreLayout == true) return
        recycler?.let {
            detachAndScrapAttachedViews(it)
        }
        var offsetX = 0
        var offsetY = 0
        var viewH = 0
        for (i in 0 until itemCount) {
            recycler?.getViewForPosition(i)?.let { view ->
                addView(view)
                //添加当前view后进行测量
                measureChildWithMargins(view, 0, 0)
                val w = getDecoratedMeasuredWidth(view)
                val h = getDecoratedMeasuredHeight(view)
                viewH = h
                var f = allItem.get(i)
                if (f == null) f = Rect()
                if (offsetX + w > width) {
                    //需要换行
                    offsetY += h
                    offsetX = w
                    f.set(0, offsetY, w, offsetY + h)
                } else {
                    f.set(offsetX, offsetY, offsetX + w, offsetY + h)
                    offsetX += w
                }
                //要针对于当前View   生成对应的Rect  然后放到allItem数组
                allItem.put(i, f)
                totalHeight = offsetY + viewH
            }
        }
        recyclerViewFillView(recycler, state)
    }

    override fun scrollVerticallyBy(
        dy: Int,
        recycler: RecyclerView.Recycler?,
        state: RecyclerView.State?
    ): Int {
        recycler?.let {
            detachAndScrapAttachedViews(it)
        }
        var t = dy
        if (verticalScrollOffset + dy < 0) {
            t = -verticalScrollOffset
        } else if (verticalScrollOffset + dy > totalHeight - height) {
            t = totalHeight - height - verticalScrollOffset

        }
        verticalScrollOffset += t
        offsetChildrenVertical(t)
        recyclerViewFillView(recycler, state)
        return t

    }

    override fun canScrollVertically(): Boolean {
        return true
    }

    private fun recyclerViewFillView(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        //清空RecyclerView的子View
        recycler?.let {
            detachAndScrapAttachedViews(it)
            val phoneFrame = Rect(0, verticalScrollOffset, width, verticalScrollOffset + height)
            //将滑出屏幕的view进行回收
            for (i in 0 until childCount) {
                val childView = getChildAt(i)
                val child: Rect = allItem.get(i)
                if (!Rect.intersects(phoneFrame, child)) {
                    removeAndRecycleView(childView!!, it)
                }
            }

            //可见区域出现在屏幕上的子view
            for (j in 0 until itemCount) {
                if (Rect.intersects(phoneFrame, allItem.get(j))) {
                    //scrap回收池里面拿的
                    val scrap = it.getViewForPosition(j)
                    measureChildWithMargins(scrap, 0, 0)
                    addView(scrap)
                    val frame: Rect = allItem.get(j)
                    layoutDecorated(
                        scrap, frame.left, frame.top - verticalScrollOffset,
                        frame.right, frame.bottom - verticalScrollOffset
                    )
                }
            }
        }
    }
}