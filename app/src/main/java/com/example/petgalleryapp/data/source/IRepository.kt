package com.example.petgalleryapp.data.source

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.supervisorScope
import retrofit2.HttpException
import java.net.SocketTimeoutException

/**
 * Execute API
 */
interface IRepository {

    /**
     * Api execution.
     */
    suspend fun <S : IResponse> execute(request: Deferred<S>): Response<S> {
        return try {
            when (val response = request.await()) {
                is ResponseBase -> {
                    return when (response.status.code) {
                        ApiStatus.API_STATUS_200 -> {
                            Response.Success(response)
                        }
                        else -> {
                            Response.Failure(ErrorResponse.createApiError(response.status.code))
                        }
                    }
                }
                else -> Response.Failure(ErrorResponse.createNetworkError())
            }
        } catch (t: Throwable) {
//            CrashlyticsHelper.sendCrashlyticsExceptionLog(t)

            when (t) {
                is SocketTimeoutException -> Response.Failure(ErrorResponse.createTimeoutError())
                is HttpException -> Response.Failure(ErrorResponse.createNotFoundError())
                else -> Response.Failure(ErrorResponse.createNetworkError())
            }
        }
    }

    /**
     * Api execution for multiple asynchronous communication.
     */
    suspend fun <S : IResponse> execute(vararg request: Deferred<S>): List<Response<S>> {
        val successResult = mutableListOf<Response<S>>()
        var failedResult = mutableListOf<Response<S>>()
        supervisorScope {
            try {
                val responseList = request.map { req ->
                    async { req.await() }
                }.awaitAll()
                responseList.forEach { response ->
                    when (response) {
                        is ResponseBase -> {
                            when (response.status.code) {
                                ApiStatus.API_STATUS_200 -> {
                                    successResult.add(Response.Success(response))
                                }
                                else -> {
                                    failedResult.add(Response.Failure(ErrorResponse.createApiError(response.status.code)))
                                    return@forEach
                                }
                            }
                        }
                        else -> {
                            failedResult.add(Response.Failure(ErrorResponse.createNetworkError()))
                            return@forEach
                        }
                    }
                }
            } catch (t: Throwable) {
//                CrashlyticsHelper.sendCrashlyticsExceptionLog(t)

                failedResult.add(when (t) {
                    is SocketTimeoutException -> Response.Failure(ErrorResponse.createTimeoutError())
                    is HttpException -> Response.Failure(ErrorResponse.createNotFoundError())
                    else -> Response.Failure(ErrorResponse.createNetworkError())
                })
            }
        }

        return if(failedResult.isEmpty()) successResult else failedResult
    }
}