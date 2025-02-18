package com.anupam.reviews.presentation.reviewlist

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.anupam.reviews.data.datasource.local.AppDataBase
import com.anupam.reviews.data.datasource.local.dao.ReviewDao
import com.anupam.reviews.data.datasource.local.dao.ReviewRemoteKeysDao
import com.anupam.reviews.data.datasource.remote.ReviewsApi
import com.anupam.reviews.data.models.dto.ReviewResponse
import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.data.repository.paging.ReviewListRemoteMediator
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.times
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@OptIn(ExperimentalPagingApi::class)
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReviewListRemoteMediatorTest {

    @Mock
    private lateinit var reviewsApi: ReviewsApi

    @Mock
    private lateinit var appDatabase: AppDataBase

    @Mock
    private lateinit var reviewDao: ReviewDao

    @Mock
    private lateinit var reviewRemoteKeysDao: ReviewRemoteKeysDao

    private val testDispatcher = UnconfinedTestDispatcher()

    private lateinit var remoteMediator: ReviewListRemoteMediator

    @Before
    fun setUp() {
        whenever(appDatabase.reviewDao).thenReturn(reviewDao)
        whenever(appDatabase.reviewRemoteKeysDao).thenReturn(reviewRemoteKeysDao)
        remoteMediator = ReviewListRemoteMediator(
            reviewsApi = reviewsApi,
            appDatabase = appDatabase,
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `test refresh load success`() = runTest {
        // Arrange
        val mockReviews = (1..10).map {
            ReviewResponse.ReviewsDto(
                id = it.toLong(),
                author = ReviewResponse.ReviewsDto.Author(
                    fullName = "Author $it",
                    country = "",
                    photo = ""
                ),
                message = "Message $it",
                enjoyment = "",
                isAnonymous = false,
                rating = 5f,
                created = "",
                optionId = 1,
                activityId = 1,
                language = "",
                travelerType = "",
                title = ""
            )
        }
        val mockResponse = ReviewResponse(
            reviews = mockReviews,
            totalCount = 10000,
            averageRating = 4.5f
        )
        whenever(reviewsApi.getReviews(0, 10)).thenReturn(mockResponse)

        // Act
        val pagingState = PagingState<Int, ReviewEntity>(
            pages = emptyList(),
            anchorPosition = null,
            config = PagingConfig(pageSize = 10),
            leadingPlaceholderCount = 0
        )
        val result = remoteMediator.load(LoadType.REFRESH, pagingState)

        // Assert
        assertThat(result).isInstanceOf(RemoteMediator.MediatorResult.Success::class.java)
        val successResult = result as RemoteMediator.MediatorResult.Success
        assertThat(successResult.endOfPaginationReached).isFalse()

        // Verify database interactions
        verify(reviewDao, times(1)).deleteAllReviews()
        verify(reviewRemoteKeysDao, times(1)).clearAllRemoteKeys()
    }
}
