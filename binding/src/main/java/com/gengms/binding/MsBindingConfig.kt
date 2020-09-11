package com.gengms.binding

import android.util.SparseArray

class MsBindingConfig(
    val layout: Int
) {
    private val variables: SparseArray<Any?> = SparseArray()
    fun getVariables() = variables
    fun addVariables(variableId: Int, value: Any?): MsBindingConfig {
        variables.put(variableId, value)
        return this
    }
}