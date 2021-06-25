package com.example.task_vodafone.ui.splash

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.task_vodafone.FakeLocalRepo
import com.example.task_vodafone.FakeRemoteRepo

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SplashViewModelTest{

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var viewModel : SplashViewModel



    @Before
    fun setup(){

        viewModel = SplashViewModel(FakeRemoteRepo(),FakeLocalRepo())
    }

    @Test
    fun testgetAirlines(){

//        viewModel.getAirLines()
//
//        //  val result=viewModel.airlineList.getOrAwaitValue()
//        viewModel.airlineList.observeForever{
//            assertNotNull(it)
//        }
    }


}