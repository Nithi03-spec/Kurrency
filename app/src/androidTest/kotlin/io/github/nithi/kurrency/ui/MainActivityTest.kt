/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.IdlingRegistry
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.MediumTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import io.github.nithi.kurrency.util.DataBindingIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import io.github.nithi.kurrency.util.monitorActivity

/**
 * A test class for [MainActivity]
 */
@RunWith(AndroidJUnit4ClassRunner::class)
@MediumTest
class MainActivityTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @Before
    fun registerIdlingResources() {
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @Test
    fun activityLaunchesAndToolbarIsDisplayed() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)
        launchMain {
            verifyToolbar()
        }
        activityScenario.close()
    }

    @After
    fun unregisterIdlingResources() {
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }
}
