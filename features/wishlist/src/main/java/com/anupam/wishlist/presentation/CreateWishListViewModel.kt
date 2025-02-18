package com.anupam.wishlist.presentation

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.anupam.wishlist.domain.usecases.WishListAddUseCase
import com.anupam.wishlist.presentation.navigation.WishListCreateRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

@HiltViewModel
class CreateWishListViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val createWishListUseCase: WishListAddUseCase
): ViewModel() {
    private val createWishListData = savedStateHandle.toRoute<WishListCreateRoute>()
    init {
        viewModelScope.launch {

        }
    }
}