package com.gengms.recyclerview.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder

/**
 *  @author gengmingshan
 *  基础ViewHolder 支持itemClick和itemLongClick事件
 */
open class MsViewHolder(itemView : View) : ViewHolder(itemView) {
    fun setOnClick(onClick : (viewHolder: MsViewHolder, position : Int) -> Unit) {
        itemView.setOnClickListener { onClick(this, layoutPosition) }
    }
    fun setOnLongClick(onLongClick : (viewHolder: MsViewHolder, position : Int) -> Boolean) {
        itemView.setOnLongClickListener { onLongClick(this, layoutPosition) }
    }
}