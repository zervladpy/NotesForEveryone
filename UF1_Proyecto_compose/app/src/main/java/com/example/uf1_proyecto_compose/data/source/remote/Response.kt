package com.example.uf1_proyecto_compose.data.source.remote

/// Api Response Handler
sealed class Response<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T>(data: T? = null) : Response<T>(data)
    class Success<T>(data: T? = null) : Response<T>(data)
    class Error<T>(data: T? = null, message: String? = null) : Response<T>(data, message)
}
