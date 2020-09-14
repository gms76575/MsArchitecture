package com.gengms.recyclerview.selectable

import android.view.View
import com.gengms.recyclerview.base.MsRecyclerAdapter
import com.gengms.recyclerview.base.MsViewHolder

/**
 * 单选/多选适配器
 */
abstract class SelectableAdapter<T>(
    @SelectMode private var selectMode: Int
) : MsRecyclerAdapter() {
    private var mOnSelectStateChangeListener :  ((selectState: Int) -> Unit)? = null
    private var mOnSelectChangeListener :  ((position : Int) -> Unit)? = null

    protected val selectList: ArrayList<Selectable<T>> = ArrayList()
    private val supportStates = ArrayList<Int>()
    private var currentState: Int? = null
    protected var singleSelectPosition: Int = 0
    fun addSupportStates(@SelectState selectState: Int): SelectableAdapter<T>{
        supportStates.add(selectState)
        return this
    }
    fun setCurrentState(@SelectState selectState: Int): SelectableAdapter<T>{
        currentState = selectState
        return this
    }
    fun setDataList(dataList: List<T>) {
        selectList.clear()
        addDataList(dataList)
    }
    fun refresh(dataList: List<T>) {
        setDataList(dataList)
        notifyDataSetChanged()
    }
    fun addDataList(dataList: List<T>) {
        for (item in dataList) {
            selectList.add(Selectable(item))
        }
    }
    fun loadMore(dataList: List<T>) {
        addDataList(dataList)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: MsViewHolder, position: Int) {
        if (holder !is SelectableViewHolder) {
            throw ClassCastException("Please return a SelectableViewHolder in method\"onCreateViewHolder\"")
        }
        if (currentState == SelectState_SELECTING) {
            holder.checkBox.visibility = View.VISIBLE
            holder.checkBox.isChecked = selectList[position].selected
            holder.setOnClick { _, pos ->
                if (selectMode == SelectMode_SINGLE && singleSelectPosition == pos) {
                    return@setOnClick
                }
                val selectItem = selectList[pos]
                selectItem.selected = !selectItem.selected
                notifyItemChanged(pos)
                if (selectMode == SelectMode_SINGLE) {
                    // 单选时取消上一次的选中
                    selectList[singleSelectPosition].selected = false
                    notifyItemChanged(singleSelectPosition)
                    singleSelectPosition = pos
                }
                if (mOnSelectChangeListener != null) {
                    mOnSelectChangeListener!!(pos)
                }
            }
            holder.setOnLongClick { _, _ -> false }
        } else {
            holder.checkBox.visibility = View.GONE
            if (mOnItemClickListener != null) {
                holder.setOnClick(mOnItemClickListener!!)
            }
            if (currentState == SelectState_LONG_CLICK_TO_SELECT) {
                holder.setOnLongClick { _, _ ->
                    if (supportStates.contains(SelectState_SELECTING)) {
                        currentState = SelectState_SELECTING
                        notifyDataSetChanged()
                        if (mOnSelectStateChangeListener != null) {
                            mOnSelectStateChangeListener!!(SelectState_SELECTING)
                        }
                    }
                    true
                }
            } else {
                if (mOnItemLongClickListener != null) {
                    holder.setOnLongClick(mOnItemLongClickListener!!)
                }
            }
        }
    }

    override fun getItemCount() = selectList.size

    /**
     * 是否有选中
     */
    fun hasSelectAnything(): Boolean {
        for (item in selectList) {
            if (item.selected) {
                return true
            }
        }
        return false
    }

    /**
     * 是否全选
     */
    fun isSelectAll(): Boolean {
        for (item in selectList) {
            if (!item.selected) {
                return false
            }
        }
        return true
    }

    /**
     * 全选/取消全选
     */
    fun selectAll(selectAll: Boolean) {
        if (selectMode == SelectMode_SINGLE || currentState != SelectState_SELECTING) {
            return
        }
        for (item in selectList) {
            item.selected = selectAll
        }
        notifyDataSetChanged()
    }

    /**
     * 获取选中数据
     */
    fun getSelectedData(): List<T> {
        val selected = ArrayList<T>()
        for (item in selectList) {
            if (item.selected) {
                selected.add(item.data)
            }
        }
        return selected
    }

    fun setOnSelectChangeListener(listener: (position : Int) -> Unit) {
        mOnSelectChangeListener = listener
    }

    fun setOnSelectStateChangeListener(listener: (selectState: Int) -> Unit) {
        mOnSelectStateChangeListener = listener
    }
}