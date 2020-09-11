package com.gengms.common.utils

import android.content.Context
import android.widget.Toast
import com.gengms.common.application.MsApp

fun toastShort(content: String) {
    Toast.makeText(MsApp.getApplication(), content, Toast.LENGTH_SHORT).show()
}

fun toastLong(content: String) {
    Toast.makeText(MsApp.getApplication(), content, Toast.LENGTH_LONG).show()
}

fun toastShort(context: Context, content: String) {
    Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
}

fun toastLong(context: Context, content: String) {
    Toast.makeText(context, content, Toast.LENGTH_LONG).show()
}