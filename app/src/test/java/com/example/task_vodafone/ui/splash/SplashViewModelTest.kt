package com.example.task_vodafone.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.task_vodafone.DummyData
import com.example.task_vodafone.repo.ILocalRepo
import com.example.task_vodafone.repo.IRemoteRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response


@RunWith(MockitoJUnitRunner::class)
class SplashViewModelTest{

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var viewModel : SplashViewModel

    @Mock
    lateinit var localRepo : ILocalRepo

    @Mock
    lateinit var remoteRepo : IRemoteRepo


    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setup()= runBlocking {
        Mockito.`when`(remoteRepo.getAirLines()).thenReturn(Response.success(DummyData.listOfAirlinesModel))
        Mockito.`when`(localRepo.getCached()).thenReturn(false)
        viewModel = SplashViewModel(remoteRepo,localRepo, TestCoroutineDispatcher())
    }

    @Test
    fun `test get airline post data cached true`(){
        viewModel.getAirLines()
        viewModel.dataCached.observeForever{
            assertEquals(it,true)
        }
    }

    @Test
    fun `test handle error take code 200 post value success`(){
        viewModel.handleError(200)
        viewModel.stateLiveData.observeForever{
            assertEquals(it,"connection success")
        }
    }
}