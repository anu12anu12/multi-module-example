package com.getyourguide.interview.presentation

import com.anupam.common.errors.DataError

data class SharedUIState(
    val isSuccessful: Boolean = true,
    val savedWishListId: String? = null,
    val error: DataError? = null,
)
