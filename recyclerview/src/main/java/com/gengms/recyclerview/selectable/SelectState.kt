package com.gengms.recyclerview.selectable
import androidx.annotation.IntDef

const val SelectState_NOT_SELECTABLE = 0
const val SelectState_LONG_CLICK_TO_SELECT = 1
const val SelectState_SELECTING = 2

@IntDef(SelectState_NOT_SELECTABLE, SelectState_LONG_CLICK_TO_SELECT, SelectState_SELECTING)
annotation class SelectState

const val SelectMode_SINGLE = 0
const val SelectMode_MULTI = 1

@IntDef(SelectMode_SINGLE, SelectMode_MULTI)
annotation class SelectMode