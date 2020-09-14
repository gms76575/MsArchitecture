package com.gengms.msarchitecture.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gengms.msarchitecture.R
import com.gengms.msarchitecture.test.bean.TestBean
import com.gengms.recyclerview.base.MsViewHolder
import com.gengms.recyclerview.selectable.SelectMode_SINGLE
import com.gengms.recyclerview.selectable.SelectState_SELECTING
import com.gengms.recyclerview.selectable.SelectableAdapter
import com.gengms.recyclerview.selectable.SelectableViewHolder

class MySingleAdapter(dataList: List<TestBean>, defaultSelected: Int = -1)
    : SelectableAdapter<TestBean>(SelectMode_SINGLE) {

    init {
        setDataList(dataList)
        if (defaultSelected >= 0 && defaultSelected < dataList.size) {
            selectList[0].selected = true
        }
        addSupportStates(SelectState_SELECTING)
        setCurrentState(SelectState_SELECTING)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_single_select, parent, false)
        return MySingleViewHolder(view, R.id.checkbox)
    }

    override fun onBindViewHolder(holder: MsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val viewHolder : MySingleViewHolder = holder as MySingleViewHolder
        val item = selectList[position].data
        viewHolder.tvCode.text = item.code
        viewHolder.tvName.text = item.name
    }

}

class MySingleViewHolder(view : View, checkViewId : Int) : SelectableViewHolder(view, checkViewId) {
    val tvCode: TextView = view.findViewById(R.id.tv_code)
    val tvName: TextView = view.findViewById(R.id.tv_name)
}