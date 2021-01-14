package com.example.petgalleryapp.data.source

/**
 * Common get result error
 */
interface TaskError

data class ApiError(val title: String = "", val code: Int, val msg: String = "") : TaskError
data class SystemError(val title: String = "", val code: Int, val msg: String = "") : TaskError
data class NetworkError(val msg: String = "") : TaskError
data class TimeoutError(val msg: String = "") : TaskError