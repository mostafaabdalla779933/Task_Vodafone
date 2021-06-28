package com.example.task_vodafone.ui.detials

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


//@RunWith(AndroidJUnit4::class)
class DetialsFragmentTest{



    @Before
    fun setup() {
    }

    @ExperimentalCoroutinesApi
    @Test
    fun taskDetails_displayedInUi() = runBlockingTest {

        val bundle = bundleOf("name" to "cairo",
            "id"   to "",
            "slogon" to "slogon",
            "country" to "country",
            "head" to "head",
            "website" to "website")

        launchFragmentInContainer<DetialsFragment>(bundle)
        Thread.sleep(3000)

        Espresso.onView(ViewMatchers.withText("cairo"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("country"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("slogon"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

        Espresso.onView(ViewMatchers.withText("head"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))

    }

}