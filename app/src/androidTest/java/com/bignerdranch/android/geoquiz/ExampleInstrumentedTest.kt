package com.bignerdranch.android.geoquiz

import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.UiDevice
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.bignerdranch.android.geoquiz", appContext.packageName)
    }
}

class MainActivityTest() {

    private lateinit var scenario: ActivityScenario<MainActivity>

    @Before
    fun setUp() {
        scenario = launch(MainActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun InstrumentalTest1() {
        onView(withId(R.id.cheat_button)).perform(click())
        onView(withId(R.id.show_answer_button)).perform(click())
        onView(withId(R.id.answer_text_view)).check(matches(withText("True")))
    }
}

class CheatActivityTest() {

    private lateinit var scenario: ActivityScenario<CheatActivity>

    @Before
    fun setUp() {
        scenario = launch(CheatActivity::class.java)
    }

    @After
    fun tearDown() {
        scenario.close()
    }

    @Test
    fun InstrumentalTest2() {
        onView(withId(R.id.show_answer_button)).perform(click())
        val device = UiDevice.getInstance(getInstrumentation())

        device.setOrientationLeft()
        Thread.sleep(15000);
        onView(withId(R.id.answer_text_view)).check(matches(withText("False")))
    }
}