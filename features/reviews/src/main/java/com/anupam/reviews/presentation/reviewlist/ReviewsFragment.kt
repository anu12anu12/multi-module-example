package com.anupam.reviews.presentation.reviewlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.anupam.reviews.presentation.reviewlist.components.ReviewListRoute
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReviewsFragment : Fragment() {

  companion object {
    fun newInstance(): Fragment = ReviewsFragment()
  }

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    return ComposeView(requireContext()).apply {
      setContent {
          ReviewListRoute(
              modifier = Modifier,
              onCreateWishList = {

              }
          )
      }
    }
  }
}