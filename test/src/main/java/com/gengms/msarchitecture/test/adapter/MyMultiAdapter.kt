package com.gengms.msarchitecture.test.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.gengms.msarchitecture.R
import com.gengms.msarchitecture.test.bean.TestBean
import com.gengms.recyclerview.base.MsViewHolder
import com.gengms.recyclerview.selectable.*

class MyMultiAdapter(dataList: List<TestBean>)
    : SelectableAdapter<TestBean>(SelectMode_MULTI) {
    init {
        setDataList(dataList)
        addSupportStates(SelectState_LONG_CLICK_TO_SELECT)
        addSupportStates(SelectState_SELECTING)
        setCurrentState(SelectState_LONG_CLICK_TO_SELECT)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_multi_select, parent, false)
        return MyMultiViewHolder(view, R.id.checkbox)
    }

    override fun onBindViewHolder(holder: MsViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val viewHolder : MyMultiViewHolder = holder as MyMultiViewHolder
        val item = selectList[position].data
        viewHolder.tvCode.text = item.code
        viewHolder.tvName.text = item.name
    }

}

class MyMultiViewHolder(view : View, checkViewId : Int) : SelectableViewHolder(view, checkViewId) {
    val tvName : TextView = view.findViewById(R.id.tv_name)
    val tvCode : TextView = view.findViewById(R.id.tv_code)
}