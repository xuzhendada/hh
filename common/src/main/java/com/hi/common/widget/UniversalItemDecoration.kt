package com.hi.common.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import androidx.recyclerview.widget.RecyclerView
import com.hi.common.R
import com.hi.common.ktx.dimen

class UniversalItemDecoration(
    context: Context,
    private val leftMargin: Int = 0,
    private val rightMargin: Int = 0,
    dividerHeightResId: Int = R.dimen.item_decoration
) : RecyclerView.ItemDecoration() {
    private val divider = context.getDrawable(R.drawable.shape_divider_item)
    private val bounds = Rect()
    private val dividerHeight = context.dimen(dividerHeightResId)
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        divider?.let {
            for (i in 0 until parent.childCount - 1) {
                val child = parent.getChildAt(i)
                val index = parent.getChildAdapterPosition(child)
                if (index != RecyclerView.NO_POSITION) {
                    parent.getDecoratedBoundsWithMargins(child, bounds)
                    it.setBounds(
                        leftMargin,
                        bounds.bottom - dividerHeight,
                        parent.width - rightMargin,
                        bounds.bottom
                    )
                    it.draw(c)
                }
            }
        }

    }
}
