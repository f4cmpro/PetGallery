package com.example.petgalleryapp.data.source

import androidx.annotation.CheckResult

/**
 * Using for common get result
 * [Success] get success
 * [Failure] get failed
 */
sealed class TaskResult<T, S> {
    data class Success<T, S>(val value: T) : TaskResult<T, S>()
    data class Failure<T, S>(val value: S) : TaskResult<T, S>()

    inline fun <X> fold(
        success: (T) -> X,
        failure: (S) -> X
    ): X = when (this) {
        is Success -> success(value)
        is Failure -> failure(value)
    }

    @CheckResult
    fun onFailure(block: (S) -> Unit): TaskResult<T, S> = when (this) {
        is Success -> this
        is Failure -> {
            block(this.value)
            this
        }
    }

    @CheckResult
    fun successOrElse(acc: T): T = when (this) {
        is Success -> value
        is Failure -> acc
    }

    @CheckResult
    fun successOrNull(): T? = when (this) {
        is Success -> value
        is Failure -> null
    }
}
