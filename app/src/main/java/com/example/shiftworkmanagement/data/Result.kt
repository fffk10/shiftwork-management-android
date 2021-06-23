package com.example.shiftworkmanagement.data

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: Exception) : Result<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "ログイン成功 画面遷移します"
            is Error -> "Error[exception=$exception]"
        }
    }
}