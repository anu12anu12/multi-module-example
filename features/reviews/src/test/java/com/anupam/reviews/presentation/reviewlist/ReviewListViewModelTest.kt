package com.anupam.reviews.presentation.reviewlist

import androidx.paging.PagingData
import androidx.paging.testing.asSnapshot
import com.anupam.reviews.TestCoRoutineRule
import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.domain.repository.ReviewsRepository
import com.anupam.reviews.domain.usecases.GetReviewsUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ReviewListViewModelTest {

    @get:Rule
    val coroutineRule = TestCoRoutineRule()
    @Mock
    private lateinit var repository: ReviewsRepository

    private lateinit var getReviewsUseCase: GetReviewsUseCase
    @Before
    fun setUp() {
        getReviewsUseCase = GetReviewsUseCase(repository)
    }

    @Test
    fun `test uiState emits correct PagingData`() = runTest {
        // Arrange
        val mockReviews = (1..20).map {
            ReviewEntity(
                id = it,
                message = "Message $it",
                rating = 5f,
                authorName = "Author $it",
                authorCountry = "",
                authorPhoto = "",
                created = "",
                reviewId = 1L
            )
        }
        val mockPagingData: Flow<PagingData<ReviewEntity>> = flowOf(PagingData.from(mockReviews))
        whenever(repository.getReviews()).thenReturn(mockPagingData)

        val viewModel = ReviewListViewModel(getReviewsUseCase)

        // Act & Assert
        viewModel.uiState.asSnapshot {
            coroutineRule.testDispatcher.scheduler.advanceUntilIdle()
            val snapshot = scrollTo(index = 19)
            assertThat(snapshot).isNotNull()
        }

        // Verify use case was called once
        verify(getReviewsUseCase, times(1)).invoke()
    }
}


