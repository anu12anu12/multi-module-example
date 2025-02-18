package com.anupam.ui.presentation.themes

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

object Colors {
  val GREY_100 = Color(0xFFEBEEF1)
  val BASKING_YELLOW = Color(0xFFFFD938)
  val BLUE_900 = Color(0xFF1A2B49)
  val WHITE = Color(0xFFFFFFFF)
  val GREY_800 = Color(0xFF31343D)
  val GUIDING_RED = Color(0xFFFE5533)
}


val labelPrimary: Color
  @Composable
  get() = if (!isSystemInDarkTheme()) Colors.BLUE_900 else Colors.WHITE

val surfaceSecondary: Color
  @Composable
  get() = if (!isSystemInDarkTheme()) Colors.GREY_100 else Colors.GREY_800
