package com.getyourguide.interview.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.anupam.common.errors.DataError
import com.anupam.wishlist.presentation.navigation.WishListCreateRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

@HiltViewModel
class SharedViewModel(
    private val savedStateHandle: SavedStateHandle
): ViewModel() {
    private val _uiState = MutableSharedFlow<SharedUIState>()
    val uiState = _uiState.asSharedFlow()
    private val createWishListData = savedStateHandle.toRoute<WishListCreateRoute>()

    fun setResult(
        isSuccessful: Boolean,
        savedWishListId: String? = null,
        error: DataError? = null
    ) {
        viewModelScope.launch {
            _uiState.emit(
                SharedUIState(
                    isSuccessful = isSuccessful,
                    savedWishListId = savedWishListId,
                    error = error
                )
            )
        }
    }
}