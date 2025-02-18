package com.anupam.reviews.data.models.dto

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewResponse(
    val reviews: List<ReviewsDto>,
    val totalCount: Int,
    val averageRating: Float,
) {

  @JsonClass(generateAdapter = true)
  data class ReviewsDto(
      val id: Long,
      val author: Author,
      val title: String?,
      val message: String?,
      val enjoyment: String?,
      val isAnonymous: Boolean?,
      val rating: Float?,
      val created: String,
      val optionId: Int?,
      val activityId: Int,
      val language: String?,
      val travelerType: String?
  ) {
    @JsonClass(generateAdapter = true)
    data class Author(
      val fullName: String?,
      val country: String?,
      val photo: String?
    )
  }
}


