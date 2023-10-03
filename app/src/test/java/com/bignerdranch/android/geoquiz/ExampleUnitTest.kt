package com.bignerdranch.android.geoquiz

import androidx.lifecycle.SavedStateHandle
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun UnitTest1() {
        val hi = SavedStateHandle()
        val hi2 = clickedAlready(hi)
        assertEquals(hi2.clickedOrNot, false)
    }

    @Test
    fun UnitTest2() {
        val hi = SavedStateHandle()
        val hi2 = clickedAlready(hi)
        hi2.clickedOrNot = true //tests if it can be modified
        assertEquals(hi2.clickedOrNot, true)
    }
}