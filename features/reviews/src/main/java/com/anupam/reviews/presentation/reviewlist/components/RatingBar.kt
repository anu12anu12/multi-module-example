package com.anupam.reviews.presentation.reviewlist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTag
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@Composable
fun RatingBar(
  modifier: Modifier = Modifier,
  rating: Float,
  rounding: Boolean = true,
) {
  var currentRating by remember(rating) { mutableFloatStateOf(rating) }
  val roundedRating = buildRoundedRating(currentRating, rounding)
  Row(
    modifier = modifier
      .height(16.dp)
      .width(IntrinsicSize.Min)
      .wrapContentWidth(),
    horizontalArrangement = Arrangement.spacedBy(1.dp),
  ) {
    for (step in 1..5) {
      val stepRating = when {
        roundedRating > step -> 1f
        step.rem(roundedRating) < 1 -> roundedRating - (step - 1f)
        else -> 0f
      }
      RatingStar(
        modifier = Modifier
          .semantics {
            testTag = "RatingStar:$step"
          },
        rating = stepRating,
      )
    }
  }
}


private fun buildRoundedRating(rating: Float, rounding: Boolean) =
  if (rounding) {
    (rating * 2).roundToInt() / 2f
  } else {
    rating
  }
