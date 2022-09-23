package com.example.androidtest

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test for MainMenu screen, which will execute on an Android device.
 *
 */
@RunWith(AndroidJUnit4::class)
class MenuScreenTest {

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Assert.assertEquals("com.example.androidtest", appContext.packageName)
    }
}