package com.gengms.architecture.request

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.gengms.architecture.exception.MsException
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

@Deprecated("")
inline fun <T> stateLiveData(crossinline request:(suspend ()->T?)) =
    androidx.lifecycle.liveData<MsResult<T>>(Dispatchers.IO) {
        emit(MsResult.loading())
        try {
            val result = request.invoke()
            if (result == null) {
                emit(MsResult.error(MsException("data is null")))
            } else {
                emit(MsResult.success(result))
            }
        } catch (ioException: Exception) {
            Log.e("--", ioException.toString())
            emit(MsResult.error(MsException("request error")))
        }
    }

fun <T> asyncRequest(liveData: MutableLiveData<MsResult<T>>, request:(suspend ()->T?)) {
    val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
        Log.e(request.toString(), throwable.toString())
        liveData.value = MsResult.error(MsException("request error"))
    }
    GlobalScope.launch(Dispatchers.Main+exceptionHandler) {
        liveData.value = MsResult.loading()
        val result = withContext(Dispatchers.IO) { request.invoke() }
        if (result == null) {
            liveData.value = MsResult.error(MsException("data is null"))
        } else {
            liveData.value = MsResult.success(result)
        }
    }
}

fun <T> asyncRequest(liveData: MutableLiveData<MsResult<T>>, arg1: Any?, request:(suspend (arg1: Any?)->T?)) {
    val exceptionHandler: CoroutineContext = CoroutineExceptionHandler { _, throwable ->
        Log.e(request.toString(), throwable.toString())
        liveData.value = MsResult.error(MsException("request error"))
    }
    GlobalScope.launch(Dispatchers.Main+exceptionHandler) {
        liveData.value = MsResult.loading()
        val result = withContext(Dispatchers.IO) { request.invoke(arg1) }
        if (result == null) {
            liveData.value = MsResult.error(MsException("data is null"))
        } else {
            liveData.value = MsResult.success(result)
        }
    }
}
