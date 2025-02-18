package com.anupam.reviews.data.repository.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.anupam.reviews.data.datasource.local.AppDataBase
import com.anupam.reviews.data.datasource.remote.ReviewsApi
import com.anupam.reviews.data.mapper.toReviewEntity
import com.anupam.reviews.data.models.entitities.ReviewEntity
import com.anupam.reviews.data.models.entitities.ReviewRemoteKeysEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class ReviewListRemoteMediator @Inject constructor(
    private val reviewsApi: ReviewsApi,
    private val appDatabase: AppDataBase,
    private val dispatcher: CoroutineDispatcher
) : RemoteMediator<Int, ReviewEntity>() {
    override suspend fun initialize(): InitializeAction {
        return InitializeAction.LAUNCH_INITIAL_REFRESH
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, ReviewEntity>
    ): MediatorResult {
        return withContext(dispatcher) {
            try {
                val offset = when (loadType) {
                    LoadType.REFRESH -> {
                        getRemoteKeyClosestToCurrentPosition(state)?.nextOffset?.minus(1) ?: 0
                    }
                    LoadType.PREPEND -> {
                        val remoteKeys = getRemoteKeyForFirstItem(state)
                        remoteKeys?.prevOffset ?: 0
                    }
                    LoadType.APPEND -> {
                        val remoteKeys = getRemoteKeyForLastItem(state)
                        remoteKeys?.nextOffset ?: 1
                    }
                }
                val limit = if (offset == 0) state.config.initialLoadSize else state.config.pageSize
                val response = reviewsApi.getReviews(offset = offset, limit = limit)
                val reviews = response.reviews
                val endOfPaginationReached = reviews.isEmpty()

                appDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        appDatabase.reviewRemoteKeysDao.clearAllRemoteKeys()
                        appDatabase.reviewDao.deleteAllReviews()
                    }

                    val keys = reviews.map { review ->
                        ReviewRemoteKeysEntity(
                            reviewId = review.id,
                            prevOffset = if (offset == 0) null else offset - 1,
                            nextOffset = if (endOfPaginationReached) null else offset + 1
                        )
                    }

                    appDatabase.reviewRemoteKeysDao.insertAll(keys)
                    appDatabase.reviewDao.insertAll(reviews.map { it.toReviewEntity() })
                }
                MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
            } catch (exception: IOException) {
                MediatorResult.Error(exception)
            } catch (exception: HttpException) {
                MediatorResult.Error(exception)
            }
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, ReviewEntity>
    ): ReviewRemoteKeysEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.reviewId?.let { id ->
                appDatabase.reviewRemoteKeysDao.getRemoteKeyById(id)
            }
        }
    }


    private suspend fun getRemoteKeyForLastItem(state: PagingState<Int, ReviewEntity>): ReviewRemoteKeysEntity? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data
            ?.lastOrNull()?.reviewId
            ?.let { review ->
                appDatabase.reviewRemoteKeysDao.getRemoteKeyById(review)
            }
    }

    private suspend fun getRemoteKeyForFirstItem(state: PagingState<Int, ReviewEntity>): ReviewRemoteKeysEntity? {
        return state.pages
            .firstOrNull { it.data.isNotEmpty() }
            ?.data
            ?.firstOrNull()?.reviewId
            ?.let { review ->
                appDatabase.reviewRemoteKeysDao.getRemoteKeyById(review)
            }
    }
}
