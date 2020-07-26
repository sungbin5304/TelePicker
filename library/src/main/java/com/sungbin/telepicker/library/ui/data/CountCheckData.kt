package com.sungbin.telepicker.library.ui.data

import android.view.View

object CountCheckData {

    var count = 0
    private var checkedList = HashMap<Int, Boolean>()
    private var views = HashMap<Int, ArrayList<View>>()
    val maxCount: Int
        get(){
            return views.size - 1
        }

    init {
        count = 0
        checkedList.clear()
        views.clear()
    }

    fun getView(id: Int) = views[id]!!
    fun setView(id: Int, view: ArrayList<View>) {
        views[id] = view
    }

    fun setChecked(id: Int, checked: Boolean) {
        checkedList[id] = checked
    }
    fun setToggle(id: Int) = setChecked(id, !getChecked(id))

    fun getChecked(id: Int) = checkedList[id]!!
}