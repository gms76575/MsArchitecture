package com.gengms.common.application

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.gengms.common.exception.MsException
import java.lang.reflect.InvocationTargetException

fun getApplicationByReflect(): Application? {
    try {
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val thread: Any = getActivityThread()
        val app = activityThreadClass.getMethod("getApplication").invoke(thread) ?: return null
        return app as Application
    } catch (e: InvocationTargetException) {
        e.printStackTrace()
    } catch (e: NoSuchMethodException) {
        e.printStackTrace()
    } catch (e: IllegalAccessException) {
        e.printStackTrace()
    } catch (e: ClassNotFoundException) {
        e.printStackTrace()
    }
    return null
}


private fun getActivityThread(): Any {
    var activityThread =
        getActivityThreadInActivityThreadStaticField()
    if (activityThread != null) return activityThread
    activityThread =
        getActivityThreadInActivityThreadStaticMethod()
    if (activityThread != null) return activityThread
    else throw MsException("get ActivityThread failed")
}

@SuppressLint("PrivateApi")
private fun getActivityThreadInActivityThreadStaticField(): Any? {
    return try {
        val activityThreadClass = Class.forName("android.app.ActivityThread")
        val sCurrentActivityThreadField =
            activityThreadClass.getDeclaredField("sCurrentActivityThread")
        sCurrentActivityThreadField.isAccessible = true
        sCurrentActivityThreadField[null]
    } catch (e: Exception) {
        Log.e("UtilsActivityLifecycle",
            "getActivityThreadInActivityThreadStaticField: " + e.message)
        null
    }
}

@SuppressLint("PrivateApi")
private fun getActivityThreadInActivityThreadStaticMethod(): Any? {
    return try {
        val activityThreadClass =
            Class.forName("android.app.ActivityThread")
        activityThreadClass.getMethod("currentActivityThread").invoke(null)
    } catch (e: java.lang.Exception) {
        Log.e("UtilsActivityLifecycle",
            "getActivityThreadInActivityThreadStaticMethod: " + e.message)
        null
    }
}