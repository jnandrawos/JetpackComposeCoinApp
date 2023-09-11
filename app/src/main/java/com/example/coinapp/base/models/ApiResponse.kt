package com.example.coinapp.base.models

data class APIResponse<out T>(
    val status: Status, val data: T?,
    val exception: Exception?,
    val message: String?
) {


    companion object {
        fun <T> success(data: T?): APIResponse<T> {
            return APIResponse(Status.SUCCESS, data, exception = null, message = null)
        }

        fun <T> error(message: String, error: Exception?): APIResponse<T> {
            return APIResponse(Status.ERROR, null, exception = error, message = message)
        }

        fun <T> loading(): APIResponse<T> {
            return APIResponse(Status.LOADING, null, null, null)
        }
    }
}

enum class Status {
    LOADING,
    ERROR,
    SUCCESS
}