package com.anupam.cordinator.service

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.anupam.cordinator.api.AppMediator
import com.anupam.cordinator.screens.Screens

class AppMediatorImpl: AppMediator {
    @Composable
    override fun NavigateTo(navController: NavHostController, screen: Screens) {
    }

    override fun getCurrentScreen(): Screens = Screens.Review
}

