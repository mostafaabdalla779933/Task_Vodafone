package com.example.task_vodafone

import android.content.Context
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(MockitoJUnitRunner::class)
class ExampleUnitTest {

    @Mock
    private lateinit var mockContext: Context

    lateinit var myObjectUnderTest :ClassUnderTest

    @Before
    fun setUp() {
        Mockito.`when`(mockContext.getString(any())).thenReturn(FAKE_STRING)

        myObjectUnderTest = ClassUnderTest(mockContext)
//        mockContext = mock<Context> {
//            on { getString(any()) } doReturn FAKE_STRING
//        }
    }
    @Test
    fun addition_isCorrect() {

        val result: String = myObjectUnderTest.getName()

        assertEquals(result, FAKE_STRING)
    }
}
private const val FAKE_STRING = "HELLO WORLD"

class ClassUnderTest(private val context: Context) {
    fun getName(): String {
        return context.getString(R.string.app_name)
    }
}