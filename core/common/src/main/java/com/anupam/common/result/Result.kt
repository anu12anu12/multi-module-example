package com.anupam.common.result

import com.anupam.common.errors.Error

typealias RootError = Error
sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val error: RootError) : Result<RootError>
}
