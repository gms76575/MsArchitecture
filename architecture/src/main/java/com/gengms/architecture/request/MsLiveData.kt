package com.gengms.architecture.request

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer

inline fun <T> androidx.lifecycle.LiveData<MsResult<T>>.observe(
        owner: LifecycleOwner,
        crossinline loading : (()->Unit),
        crossinline success:((T)->Unit),
        crossinline error:((String)->Unit)) {
    observe(owner, Observer {
        when (it.state) {
            MsResult.State.SUCCESS ->
                if(null == it.data) error("data is null") else success(it.data)
            MsResult.State.ERROR -> error(it.exception!!.message!!)
            MsResult.State.LOADING -> loading()
        }
    })
}

