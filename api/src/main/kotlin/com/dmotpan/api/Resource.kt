package com.dmotpan.api

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Loading<out T>(val data: T? = null) : Resource<T>()
    data class Failure<out T>(val error: Throwable, val data: T? = null) : Resource<T>()
}
