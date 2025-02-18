package com.anupam.cordinator.api

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.anupam.cordinator.screens.Screens

interface AppMediator {
    @Composable
    fun NavigateTo(navController: NavHostController, screen: Screens)
    fun getCurrentScreen(): Screens
}