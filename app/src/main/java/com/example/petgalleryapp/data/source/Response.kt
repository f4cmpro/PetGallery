package com.example.petgalleryapp.data.source

import androidx.annotation.CheckResult

/**
 * レスポンスのラッピングクラス
 *
 * [Success]は成功レスポンス[IResponse]を継承した任意のResponseを扱える
 * [Failure]はエラーレスポンス。throwerbleではなく、アプリ内別実装と併せ[ErrorResponse]で表現している
 *
 */

interface IResponse

sealed class Response<T : IResponse> {
    data class Success<T : IResponse>(val value: T) : Response<T>()
    data class Failure<T : IResponse>(val value: ErrorResponse) : Response<T>()

    inline fun <X> fold(
        success: (T) -> X,
        failure: (ErrorResponse) -> X
    ): X = when (this) {
        is Success -> success(value)
        is Failure -> failure(value)
    }

    @CheckResult
    fun onFailure(block: (ErrorResponse) -> Unit): Response<T> = when (this) {
        is Success -> this
        is Failure -> {
            block(this.value)
            this
        }
    }

    /**
     * 成功orデフォルト値
     * エラーを捨てていい場合のみ使用
     */
    @CheckResult
    fun successOrElse(acc: T): T = when (this) {
        is Success -> value
        is Failure -> acc
    }

    /**
     * EitherをOptionに変換
     * エラーを捨てていい場合のみ使用
     */
    @CheckResult
    fun successOrNull(): T? = when (this) {
        is Success -> value
        is Failure -> null
    }
}