package com.anupam.sharedtests.utils

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@OptIn(ExperimentalCoroutinesApi::class)
class TestCoRoutineRule: TestWatcher() {
    private val testDispatcher = StandardTestDispatcher()
    override fun starting(description: Description?) {
        Dispatchers.setMain(testDispatcher)
    }
    override fun finished(description: Description?) {
        Dispatchers.resetMain()
    }
}