package com.anupam.reviews.presentation.reviewlist.components.extensions

import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect

internal fun Modifier.starClip(rating: Float): Modifier {
    return this.drawWithContent {
        clipRect(right = size.width * rating) {
            this@drawWithContent.drawContent()
        }
    }
}