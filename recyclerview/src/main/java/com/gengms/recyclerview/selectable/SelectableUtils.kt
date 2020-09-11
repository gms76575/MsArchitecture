package com.gengms.recyclerview.selectable

fun <T> newSelectableList(dataList : List<T>) : MutableList<Selectable<T>> = MutableList(dataList.size) {
    Selectable(dataList[it])
}

fun <T> getSelectedList(selectableList : MutableList<Selectable<T>>) : MutableList<T> {
    val selectedList = ArrayList<T>()
    for (selectable in selectableList) {
        if (selectable.selected) {
            selectedList.add(selectable.data)
        }
    }
    return selectedList
}