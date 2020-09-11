package com.gengms.architecture

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner

fun getAppViewModelProvider(activity: Activity): ViewModelProvider {
    val app = checkApplication(activity) as MyApplication
    return ViewModelProvider(app, app.getAppFactory())
}

fun getAppViewModelProvider(fragment: Fragment): ViewModelProvider {
    return getAppViewModelProvider(fragment.requireActivity())
}

fun getViewModelProvider(activity: FragmentActivity): ViewModelProvider {
    return ViewModelProvider(activity)
}

fun getViewModelProvider(fragment: Fragment): ViewModelProvider {
    return ViewModelProvider(fragment.requireActivity())
}

private fun checkApplication(activity: Activity): Application {
    return activity.application
        ?: throw IllegalStateException(
            "Your activity is not yet attached to the Application instance." +
                    "You can't request ViewModel before onCreate call.")
}

class MyApplication : Application(), ViewModelStoreOwner {
    private lateinit var mAppViewModelStore: ViewModelStore
    private lateinit var mFactory: ViewModelProvider.Factory

    override fun getViewModelStore(): ViewModelStore {
        return mAppViewModelStore
    }

    fun getAppFactory(): ViewModelProvider.Factory {
        return mFactory
    }

    override fun onCreate() {
        super.onCreate()
        mAppViewModelStore = ViewModelStore()
        mFactory = ViewModelProvider
            .AndroidViewModelFactory
            .getInstance(this)
    }

}