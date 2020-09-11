package com.gengms.recyclerview.selectable

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import com.gengms.recyclerview.base.MsRecyclerAdapter
import com.gengms.recyclerview.base.MsViewHolder

/**
 * 单选/多选适配器
 */
abstract class SelectableAdapter<T>(
    protected val dataList: List<Selectable<T>>,
    private var selectMode: SelectMode
) : MsRecyclerAdapter() {

    private var selectState: SelectState = SelectState.DOING
    protected var singleSelectItem: Selectable<T>? = null
    private var mOnSelectStateChangeListener :  ((selectState: SelectState) -> Unit)? = null
    private var mOnSelectChangeListener :  ((position : Int) -> Unit)? = null

    init {
        if (selectMode == SelectMode.LONG_CLICK_MULTI) {
            selectState = SelectState.DONE
        }
    }

    fun selectItem(position: Int, select : Boolean) {
        dataList[position].selected = select
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsViewHolder {
        return onCreateSelectableViewHolder(parent, viewType)
    }

    abstract fun onCreateSelectableViewHolder(parent: ViewGroup, viewType: Int): SelectableViewHolder

    override fun onBindViewHolder(holder: MsViewHolder, position: Int) {
        val selectableHolder: SelectableViewHolder = holder as SelectableViewHolder
        when (selectMode) {
            SelectMode.SINGLE -> {
                onBindSingleMode(selectableHolder, position)
            }
            SelectMode.FIXED_MULTI -> {

            }
            else -> {
                onBindMultiMode(selectableHolder, position)
            }
        }
    }

    private fun onBindSingleMode(selectableHolder: SelectableViewHolder, pos: Int) {
        selectableHolder.checkBox.visibility = View.VISIBLE
        selectableHolder.checkBox.isChecked = dataList[pos].selected
        selectableHolder.setOnClick { _, position ->
            val isChecked = !selectableHolder.checkBox.isChecked
            dataList[position].selected = isChecked
            selectableHolder.checkBox.isChecked = isChecked
        }
        selectableHolder.setOnLongClick { _, _ ->
            false
        }
    }

    private fun onBindMultiMode(selectableHolder: SelectableViewHolder, pos: Int) {
        if (selectState == SelectState.DONE) {
            selectableHolder.checkBox.visibility = View.GONE
            holder.setOnLongClick(this::onItemLongClicked)
            if (mOnItemClickListener != null) {
                holder.setOnClick(mOnItemClickListener!!)
            }
        } else {
            selectableHolder.checkBox.visibility = View.VISIBLE
            selectableHolder.checkBox.isChecked = dataList[position].selected
            if (mOnItemLongClickListener != null) {
                holder.setOnItemLongClickListener(mOnItemLongClickListener!!)
            }
            holder.setOnItemClickListener(this::onItemClicked)
        }
    }

    /**
     * 长按开始选择（多选)
     */
    private fun onItemLongClicked(viewHolder: MsViewHolder, position: Int): Boolean {
        selectState = SelectState.DOING
        notifyDataSetChanged()
        if (mOnSelectStateChangeListener != null) {
            mOnSelectStateChangeListener!!.invoke(SelectState.DOING)
        }
        return true
    }

    private fun onItemClicked(viewHolder: MsViewHolder, position: Int) {
        if (selectMode == SelectMode.SINGLE) {
            singleSelect(viewHolder, position)
        } else {
            multiSelect(viewHolder, position)
        }
    }

    /**
     * 多选改变item选择状态
     */
    private fun multiSelect(viewHolder: MsViewHolder, position: Int) {
        val isSelected = dataList[position].selected
        dataList[position].selected = !isSelected
        Handler().post { notifyItemChanged(position) }
        if (mOnSelectChangeListener != null) {
            mOnSelectChangeListener!!.invoke(position)
        }
    }

    /**
     * 单选选中item
     */
    private fun singleSelect(viewHolder: MsViewHolder, position: Int) {
        if (singleSelectItem != null) {
            singleSelectItem!!.selected = false
        }
        dataList[position].selected = true
        singleSelectItem = dataList[position]
        notifyDataSetChanged()
        if (mOnSelectChangeListener != null) {
            mOnSelectChangeListener!!.invoke(position)
        }
    }

    /**
     * 点击返回键，在container返回键监听处调用
     */
    fun onBackClicked(): Boolean {
        if (selectMode == SelectMode.LONG_CLICK_MULTI && selectState == SelectState.DOING) {
            selectState = SelectState.DONE
            notifyDataSetChanged()
            if (mOnSelectStateChangeListener != null) {
                mOnSelectStateChangeListener!!.invoke(SelectState.DONE)
            }
            return true
        }
        return false
    }

    /**
     * 完成选择时改变选择状态
     */
    fun onSelectComplete() {
        if (selectMode == SelectMode.LONG_CLICK_MULTI && selectState == SelectState.DOING) {
            selectState = SelectState.DONE
            notifyDataSetChanged()
        }
    }

    /**
     * 是否有选中
     */
    fun hasSelectAnything(): Boolean {
        for (item in dataList) {
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
        for (item in dataList) {
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
        if (selectMode == SelectMode.SINGLE || selectState == SelectState.DONE) {
            return
        }
        for (item in dataList) {
            item.selected = selectAll
        }
        notifyDataSetChanged()
    }

    /**
     * 获取选中数据
     */
    fun getSelectedData(): List<T> {
        val selected = ArrayList<T>()
        for (item in dataList) {
            if (item.selected) {
                selected.add(item.data)
            }
        }
        return selected
    }

    fun onComplete() {
        if (selectMode == SelectMode.LONG_CLICK_MULTI && selectState == SelectState.DOING) {
            selectState = SelectState.DONE
            notifyDataSetChanged()
        }
    }

    fun setOnSelectChangeListener(listener: (position : Int) -> Unit) {
        mOnSelectChangeListener = listener
    }

    fun setOnSelectStateChangeListener(listener: (selectState: SelectState) -> Unit) {
        mOnSelectStateChangeListener = listener
    }
}