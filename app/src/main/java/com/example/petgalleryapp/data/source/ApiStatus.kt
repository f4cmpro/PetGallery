package com.example.petgalleryapp.data.source

class ApiStatus {
    companion object {
        const val API_STATUS_INITIALIZED = -1

        /**
         * Successful response
         */
        const val API_STATUS_200 = 200

        /**
         * Error response
         */
        const val API_STATUS_400 = 400

        /**
         * Unauthorized
         */
        const val API_STATUS_401 = 401

        /**
         * Server error response
         */
        const val API_STATUS_500 = 500
    }
}