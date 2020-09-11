package com.gengms.recyclerview.decoration

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.Dimension
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration

class MsDividerItemDecoration(private var orientation: Int,
                              private var color: Int,
                              private var lineWidth: Int,
                              private var startSpace: Int,
                              private var endSpace: Int,
                              private var divider: Drawable? = null) :
    ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (RecyclerView.HORIZONTAL == orientation) {
            outRect.bottom = lineWidth
        } else {
            outRect.right = lineWidth
        }
    }
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (orientation == RecyclerView.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left = parent.paddingLeft + startSpace
        val right = parent.width - parent.paddingRight - endSpace
        var top: Int?
        var bottom: Int?
        val childCount = parent.childCount
        var child: View?
        var params: RecyclerView.LayoutParams?
        for (i in 0 until childCount) {
            //去掉最后一条的分割线
            if (i == childCount-1) break
            child = parent.getChildAt(i)
            params = parent.getChildAt(i).layoutParams as RecyclerView.LayoutParams
            top = child.bottom + params.bottomMargin
            if (divider == null) {
                val paint = Paint()
                paint.color = color
                paint.strokeWidth = lineWidth.toFloat()
                canvas.drawLine(left.toFloat(), top.toFloat(), right.toFloat(), top.toFloat(), paint)
            } else {
                bottom = top + lineWidth
                divider!!.setBounds(left, top, right, bottom)
                divider!!.draw(canvas)
            }

        }
    }

    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top = parent.paddingTop + startSpace
        val bottom = parent.height - parent.paddingBottom - endSpace
        var left: Int?
        var right: Int?
        var child: View?
        var params: RecyclerView.LayoutParams?
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            child = parent.getChildAt(i)
            params = child.layoutParams as RecyclerView.LayoutParams
            left = child.right + params.rightMargin
            if (divider == null) {
                val paint = Paint()
                paint.color = color
                paint.strokeWidth = lineWidth.toFloat()
                canvas.drawLine(left.toFloat(), top.toFloat(), left.toFloat(), bottom.toFloat(), paint)
            } else {
                right = left + lineWidth
                divider!!.setBounds(left, top, right, bottom)
                divider!!.draw(canvas)
            }
        }
    }

    class Builder() {
        private var orientation: Int = RecyclerView.VERTICAL
        private var color: Int = Color.LTGRAY
        private var lineWidth: Int = 1
        private var startSpace: Int = 0
        private var endSpace: Int = 0
        private var divider: Drawable? = null
        fun create(): ItemDecoration = MsDividerItemDecoration(orientation,color,lineWidth,startSpace,endSpace)
        fun setOrientation(@RecyclerView.Orientation orientation: Int): Builder {
            this.orientation = orientation
            return this
        }
        fun setColor(@ColorRes color: Int): Builder {
            this.color = color
            return this
        }
        fun setLineWidth(@Dimension lineWidth: Int): Builder {
            this.lineWidth = lineWidth
            return this
        }
        fun setSpace(@Dimension startSpace: Int, @Dimension endSpace: Int): Builder {
            this.startSpace = startSpace
            this.endSpace = endSpace
            return this
        }
    }
}