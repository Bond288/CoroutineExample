package ru.tilk.coroutineexample.base.view

import android.view.View

var View.visible: Boolean
get() = this.visibility == View.VISIBLE
set(value) {
    visibility = if (value) {
        View.VISIBLE
    } else {
        View.GONE
    }
}