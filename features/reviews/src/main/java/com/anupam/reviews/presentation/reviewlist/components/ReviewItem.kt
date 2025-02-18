package com.anupam.reviews.presentation.reviewlist.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.anupam.reviews.R
import com.anupam.reviews.domain.model.Review
import com.anupam.ui.presentation.themes.Colors
import com.anupam.ui.presentation.themes.labelPrimary
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ReviewItem(
    review: Review,
    onItemClick: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onItemClick(review.authorName)  }
    ) {
        val rating = review.rating
        val message = review.message
        val authorName = review.authorName
        val authorCountry = review.authorCountry
        val authorPhoto = review.authorPhoto
        val dateText = LocalDateTime.parse(
            review.created,
            DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz")
        ).format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))

        Text(
            text = dateText,
            style = MaterialTheme.typography.bodySmall,
            color = labelPrimary,
        )

        Spacer(modifier = Modifier.height(10.dp))

        RatingBar(rating = rating)

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium,
            color = labelPrimary
        )

        Spacer(modifier = Modifier.height(6.dp))

        Row(
            modifier = Modifier
                .padding(bottom = 10.dp)
        ) {
            AsyncImage(
                model = authorPhoto,
                contentDescription = null,
                modifier = Modifier
                    .height(40.dp)
                    .width(40.dp)
                    .clip(CircleShape)
                    .background(Colors.GUIDING_RED)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(6.dp))

            Column(
                modifier = Modifier.align(Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(id = R.string.reviewed_by),
                    style = MaterialTheme.typography.bodySmall,
                    color = labelPrimary,
                )
                Text(
                    text = "${authorName} - ${authorCountry}",
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = labelPrimary,
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
                .background(Color.DarkGray)
        )
    }
}
