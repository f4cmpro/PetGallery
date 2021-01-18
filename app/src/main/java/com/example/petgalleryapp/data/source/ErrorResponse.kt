package com.example.petgalleryapp.data.source

import com.example.petgalleryapp.PGApplication

/**
 * Error response
 */
open class ErrorResponse(
    val code: Int = 0,
    val title: String = "",
    val msg: String = ""
) {

    companion object {
        // Local error code

        // Network error
        const val ERROR_NETWORK: Int = 900

        // Not found error
        const val ERROR_NOT_FOUND: Int = 404

        // Timeout error
        const val ERROR_TIME_OUT: Int = 408

        private val context = PGApplication.context


        /**
         * Create a network rrror
         *
         * @return {@link ErrorResponse}
         */
        fun createNetworkError(): ErrorResponse {
            val msg = "Network Error"
            return ErrorResponse(ERROR_NETWORK, "", msg)
        }

        /**
         * Create timeout error
         *
         * @return {@link ErrorResponse}
         */
        fun createTimeoutError(): ErrorResponse {
            val msg = "Timeout Error"
            return ErrorResponse(ERROR_TIME_OUT, "", msg)
        }

        /**
         * Create not found error
         *
         * @return {@link ErrorResponse}
         */
        fun createNotFoundError(): ErrorResponse {
            val msg = "Not Found Error"
            return ErrorResponse(ERROR_NOT_FOUND, "", msg)
        }

        /**
         * Create an API error
         *
         * @param code   API status code
         * @return {@link ErrorResponse}
         */
        fun createApiError(code: Int): ErrorResponse {
            val msg = when (code) {
                ApiStatus.API_STATUS_400 -> "Error response"
                ApiStatus.API_STATUS_401 -> "Unauthorized"
                ApiStatus.API_STATUS_500 -> "Server error response"
                else -> ""
            }
            return ErrorResponse(code, "", msg)
        }
    }

}
