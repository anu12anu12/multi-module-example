package com.anupam.cordinator.screens


import kotlinx.serialization.Serializable

@Serializable
sealed class Screens {
    @Serializable
    data object Review: Screens()

    @Serializable
    data object Wishlist: Screens()
}

