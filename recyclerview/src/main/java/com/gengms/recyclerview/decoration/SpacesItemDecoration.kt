package com.gengms.recyclerview.decoration

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
class SpacesItemDecoration(private val space: Int, @param:RecyclerView.Orientation private val orientation: Int) :
    ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        if (RecyclerView.HORIZONTAL == orientation) {
            outRect.top = space
            outRect.bottom = space
        } else {
            outRect.left = space
            outRect.right = space
        }
    }

}