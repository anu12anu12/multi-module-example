package com.anupam.common.errors

import com.anupam.common.errors.DataError.NetworkError.REQUEST_TIMEOUT
import com.anupam.common.errors.DataError.NetworkError.SERVER_ERROR
import com.anupam.common.errors.DataError.NetworkError.TOO_MANY_REQUESTS
import com.anupam.common.errors.DataError.NetworkError.UNKNOWN

fun Int.toNetworkError(): DataError {
    return when (this) {
        408 -> REQUEST_TIMEOUT
        429 -> TOO_MANY_REQUESTS
        500 -> SERVER_ERROR
        else -> UNKNOWN
    }
}