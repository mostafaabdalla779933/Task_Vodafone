package com.example.task_vodafone.ui.airlines

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.task_vodafone.FakeLocalRepo
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
class AirLineViewModelTest{

    @get:Rule
    var instantTaskExecutorRule= InstantTaskExecutorRule()

    private lateinit var viewModel : AirLineViewModel

    @Before
    fun setup(){

        viewModel = AirLineViewModel(FakeLocalRepo())
    }

    @Test
    fun testgetAirlines(){

        viewModel.getAirLines()
        viewModel.airlineList.observeForever{
            assertNotNull(it)
        }
    }


}

