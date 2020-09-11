package com.gengms.recyclerview.base

import androidx.recyclerview.widget.RecyclerView

abstract class MsRecyclerAdapter : RecyclerView.Adapter<MsViewHolder>(){
    protected var mOnItemClickListener :  ((viewHolder: MsViewHolder, position : Int) -> Unit)? = null
    protected var mOnItemLongClickListener : ((viewHolder: MsViewHolder, position : Int) -> Boolean)?= null

    override fun onBindViewHolder(holder: MsViewHolder, position: Int) {
        if (mOnItemClickListener != null) {
            holder.setOnClick(mOnItemClickListener!!)
        }
        if (mOnItemLongClickListener != null) {
            holder.setOnLongClick(mOnItemLongClickListener!!)
        }
    }
    fun setOnItemClickListener(onItemClickListener: (viewHolder: MsViewHolder, position : Int) -> Unit) {
        mOnItemClickListener = onItemClickListener
    }
    fun setOnItemLongClickListener(onItemLongClickListener : (viewHolder: MsViewHolder, position : Int) -> Boolean) {
        mOnItemLongClickListener = onItemLongClickListener
    }
}