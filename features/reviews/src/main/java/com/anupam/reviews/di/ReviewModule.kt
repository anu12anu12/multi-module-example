package com.anupam.reviews.di

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.anupam.common.di.IODispatcher
import com.anupam.reviews.data.datasource.local.AppDataBase
import com.anupam.reviews.data.datasource.remote.ReviewsApi
import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.data.repository.ReviewsRepositoryImpl
import com.anupam.reviews.data.repository.paging.ReviewListRemoteMediator
import com.anupam.reviews.domain.repository.ReviewsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import retrofit2.Retrofit

@OptIn(ExperimentalPagingApi::class)
@Module
@InstallIn(SingletonComponent::class)
object ReviewModule {
    @Provides
    fun provideReviewApi(retrofit: Retrofit): ReviewsApi {
        return retrofit.create(ReviewsApi::class.java)
    }

    @Provides
    fun providesReviewsPager(
        dataBase: AppDataBase,
        reviewApi: ReviewsApi,
        @IODispatcher dispatcher: CoroutineDispatcher
    ): Pager<Int, ReviewEntity> {
        return Pager(
            PagingConfig(
                pageSize = 60,               // Matches API page size
                enablePlaceholders = false,
                initialLoadSize = 120,
                prefetchDistance = 10,
                maxSize = 300
            ),
            pagingSourceFactory = { dataBase.reviewDao.getPagerSource() },
            remoteMediator = ReviewListRemoteMediator(
                reviewsApi = reviewApi,
                appDatabase = dataBase,
                dispatcher = dispatcher
            )
        )
    }
    @Provides
    fun provideReviewRepository(pager: Pager<Int, ReviewEntity>): ReviewsRepository {
        return ReviewsRepositoryImpl(pager)
    }
}