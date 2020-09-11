package com.gengms.common.utils

import android.os.Build
import androidx.annotation.ColorRes
import androidx.annotation.DimenRes
import androidx.annotation.StringRes
import com.gengms.common.application.MsApp
import java.util.Locale

fun getSystemLocale(): Locale {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        MsApp.getApplication().resources.configuration.locales[0]
    } else {
        MsApp.getApplication().resources.configuration.locale
    }
}

fun getString(@StringRes resId: Int) = MsApp.getApplication().getString(resId)

fun getColor(@ColorRes resId: Int) = MsApp.getApplication().resources.getColor(resId)

fun getDimensionPixelSize(@DimenRes resId: Int) = MsApp.getApplication().resources.getDimensionPixelSize(resId)

fun getDimensionPixelOffset(@DimenRes resId: Int) = MsApp.getApplication().resources.getDimensionPixelOffset(resId)

fun getDimension(@DimenRes resId: Int) = MsApp.getApplication().resources.getDimension(resId)

fun getId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "id", MsApp.getApplication().packageName)
}

fun getStringId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "string", MsApp.getApplication().packageName)
}

fun getDrawableId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "drawable", MsApp.getApplication().packageName)
}

fun getMipmapId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "mipmap", MsApp.getApplication().packageName)
}

fun getLayoutId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "layout", MsApp.getApplication().packageName)
}

fun getStyleId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "style", MsApp.getApplication().packageName)
}

fun getColorId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "color", MsApp.getApplication().packageName)
}

fun getDimenId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "dimen", MsApp.getApplication().packageName)
}

fun getAnimId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "anim", MsApp.getApplication().packageName)
}

fun getMenuId(resName: String): Int {
    return MsApp.getApplication().resources.getIdentifier(resName, "menu", MsApp.getApplication().packageName)
}