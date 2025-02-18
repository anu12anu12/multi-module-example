package com.anupam.reviews.presentation.reviewlist.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.res.painterResource
import com.anupam.reviews.R
import com.anupam.reviews.presentation.reviewlist.components.extensions.starClip
import com.anupam.ui.presentation.themes.Colors
import com.anupam.ui.presentation.themes.surfaceSecondary


@Composable
internal fun RatingStar(
    modifier: Modifier = Modifier,
    rating: Float,
) {
    val starDrawable = R.drawable.ic_star_fill
    Box(
        modifier = modifier
            .fillMaxHeight()
            .aspectRatio(1f),
    ) {
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .drawWithContent {
                    clipRect(left = size.width * rating) {
                        this@drawWithContent.drawContent()
                    }
                },
            painter = painterResource(id = starDrawable),
            tint = surfaceSecondary,
            contentDescription = null,
        )
        Icon(
            modifier = Modifier
                .fillMaxSize()
                .starClip(rating),
            painter = painterResource(id = starDrawable),
            tint = Colors.BASKING_YELLOW,
            contentDescription = null,
        )
    }
}