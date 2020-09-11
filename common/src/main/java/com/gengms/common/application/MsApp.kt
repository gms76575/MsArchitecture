package com.gengms.common.application

import android.app.Application

class MsApp {
    companion object {
        private var sInstance : Application? = null
        fun init(application: Application?) {
            sInstance = application
        }
        fun getApplication() : Application {
            if (sInstance == null) {
                val application = getApplicationByReflect()
                init(application)
            }
            return sInstance!!
        }
    }
}