package com.anupam.common.errors

sealed interface DataError: Error {

    enum class NetworkError: DataError {
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        PAYLOAD_TOO_LARGE,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN,
        NONE;

        companion object {
            fun toError(code: Int): DataError {
                return when (code) {
                    408 -> REQUEST_TIMEOUT
                    429 -> TOO_MANY_REQUESTS
                    500 -> SERVER_ERROR
                    else -> UNKNOWN
                }
            }
        }
    }

    enum class LocalError: DataError {
        DISK_FULL,
        UNKNOWN
    }
}