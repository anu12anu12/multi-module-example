package com.anupam.cordinator.di

import com.anupam.cordinator.api.AppMediator
import com.anupam.cordinator.service.AppMediatorImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object NavigationModule {
    @Provides
    fun provideAppNavigator(): AppMediator {
        return AppMediatorImpl()
    }

}