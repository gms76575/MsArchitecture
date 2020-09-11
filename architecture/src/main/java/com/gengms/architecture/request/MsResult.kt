package com.gengms.architecture.request

data class MsResult<T>(val state : State, val data : T?, val exception: Exception?) {

    companion object {
        fun <T> success(data : T) : MsResult<T> = MsResult(State.SUCCESS, data, null)
        fun <T> error(exception: Exception) : MsResult<T> = MsResult(State.ERROR, null, exception)
        fun <T> loading() : MsResult<T> = MsResult(State.LOADING, null, null)
    }

    enum class State{
        LOADING,SUCCESS,ERROR
    }

}
