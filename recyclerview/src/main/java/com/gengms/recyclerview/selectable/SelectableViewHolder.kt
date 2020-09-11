package com.gengms.recyclerview.selectable

import android.view.View
import android.widget.CompoundButton
import com.gengms.recyclerview.base.MsViewHolder

open class SelectableViewHolder(view : View, checkViewId : Int) : MsViewHolder(view) {
    val checkBox : CompoundButton = view.findViewById(checkViewId)
    init {
        checkBox.isClickable = false
        checkBox.isFocusableInTouchMode = false
        checkBox.isFocusable = false
    }

}