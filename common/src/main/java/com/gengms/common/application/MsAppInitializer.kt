package com.gengms.common.application

import android.app.Application
import android.content.Context
import androidx.startup.Initializer

class MsAppInitializer : Initializer<Unit> {

    override fun create(context: Context) {
        MsApp.init(context as Application)
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()

}
