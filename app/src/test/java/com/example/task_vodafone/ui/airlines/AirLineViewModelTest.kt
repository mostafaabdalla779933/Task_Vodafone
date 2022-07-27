package com.example.task_vodafone.ui.airlines

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.entity.AirLineEntity
import com.example.task_vodafone.DummyData
import com.example.task_vodafone.FakeLocalRepo
import com.example.task_vodafone.repo.ILocalRepo
import com.example.task_vodafone.repo.LocalRepo
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class AirLineViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: AirLineViewModel

    @Mock
    lateinit var localRepo : ILocalRepo

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup()  = runBlocking {
        Mockito.`when`(localRepo.getAirlines()).thenReturn(DummyData.listOfAirlinesEntities)
        viewModel = AirLineViewModel(localRepo, TestCoroutineDispatcher())
    }

    @Test
    fun `test getAirlines() `() = runBlocking {

        viewModel.getAirLines()
        val value = viewModel.airlineList.value
        assertEquals(DummyData.listOfAirlinesEntities,value)
    }


}

