package com.example.petgalleryapp.data.source

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * アプリAPIレスポンスのベースクラス
 *
 */
@JsonClass(generateAdapter = true)
open class ResponseBase : IResponse {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // Header
    @Json(name = "status") var status: Status = Status()

    ////////////////////////////////////////////////////////////////////////////////////////////////
    // public method

    /**
     * レスポンスステータスが200か否か
     *
     * @return true:正 false:否
     */
    fun isApiStatus200(): Boolean {
        return ApiStatus.API_STATUS_200 == status.code
    }

}

@JsonClass(generateAdapter = true)
data class Status(
    @Json(name = "code") var code: Int = ApiStatus.API_STATUS_200, //TODO: for debug, will be changed later
    @Json(name = "msg") var msg: String = ""                               // メッセージ
)