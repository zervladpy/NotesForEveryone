package com.example.uf1_proyecto_compose.utils

/**
 * Wrapper Response class, defines the request status
 *  @param data @Nullable payload
 *  @param message @Nullable error message
 * */
sealed class Response<T>(val data: T? = null, val message: String? = null) {

    /**
     * Success Response
     * @param data @Nullable payload
     * */
    class Success<T>(data: T? = null) : Response<T>(data)

    /**
     * Loading Response
     * @param data @Nullable payload
     * */
    class Loading<T>(data: T? = null) : Response<T>(data)

    /**
     * Error Response
     * @param data @Nullable payload
     * @param message error message
     * */
    class Error<T>(message: String, data: T? = null) : Response<T>(data, message)

}
