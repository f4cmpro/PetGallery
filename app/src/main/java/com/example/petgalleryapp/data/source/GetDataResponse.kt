package jp.co.rakuten.livestreaming.data.source.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import jp.co.rakuten.livestreaming.data.source.remote.api.ResponseBase

@JsonClass(generateAdapter = true)
data class GetDataResponse <T>(
    @Json(name = "data") val data: T
) : ResponseBase()