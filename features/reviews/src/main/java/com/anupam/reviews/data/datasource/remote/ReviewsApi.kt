package com.anupam.reviews.data.datasource.remote

import com.anupam.reviews.data.models.dto.ReviewResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ReviewsApi {
  @GET("activities/141648/reviews")
  suspend fun getReviews(
    @Query("offset") offset: Int,
    @Query("limit") limit: Int
  ): ReviewResponse

}