//package com.gengms.msarchitecture.test.adapter
//
//import android.content.Context
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import com.gengms.baserecycler.BaseViewHolder
//import com.gengms.baserecycler.selectable.SelectMode
//import com.gengms.baserecycler.selectable.SelectableRecyclerAdapter
//import com.gengms.baserecycler.selectable.SelectableViewHolder
//
//class MyMultiAdapter(context: Context, dataList: List<TestBean>, selectMode: SelectMode)
//    : SelectableRecyclerAdapter<TestBean>(context, dataList, selectMode) {
//    override fun onCreateSelectableViewHolder(
//        parent: ViewGroup,
//        viewType: Int
//    ): SelectableViewHolder {
//        val view =
//            LayoutInflater.from(context).inflate(R.layout.item_multi_select, parent, false)
//        return MyMultiViewHolder(view, R.id.checkbox)
//    }
//
//    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
//        super.onBindViewHolder(holder, position)
//        val viewHolder : MyMultiViewHolder = holder as MyMultiViewHolder
//        val item = dataList[position].data
//        viewHolder.tvCode.text = item.code
//        viewHolder.tvName.text = item.name
//    }
//
//}
//
//class MyMultiViewHolder(view : View, checkViewId : Int) : SelectableViewHolder(view, checkViewId) {
//    val tvName : TextView = view.findViewById(R.id.tv_name)
//    val tvCode : TextView = view.findViewById(R.id.tv_code)
//}