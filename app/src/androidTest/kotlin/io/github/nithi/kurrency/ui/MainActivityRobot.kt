/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import io.github.nithi.kurrency.R

/**
 * Robot pattern to verify [MainActivity]'s assertions in [MainActivityTest]
 *
 * Credit: https://academy.realm.io/posts/kau-jake-wharton-testing-robots/
 */
fun launchMain(func: MainActivityRobot.() -> Unit) = MainActivityRobot().apply { func() }

/**
 * A robot that includes [MainActivityTest]'s assertions.
 */
class MainActivityRobot {

    fun verifyToolbar() {
        onView(withId(R.id.toolbar)).check(matches(isDisplayed()))
    }
}
