/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.shared.ext

import io.github.nithi.kurrency.data.shared.rule.CoroutinesTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest

/**
 * Runs test with the current test dispatcher of [CoroutinesTestRule].
 *
 * @param block The code block should be run
 */
@ExperimentalCoroutinesApi
fun CoroutinesTestRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
    testDispatcher.runBlockingTest(block)
}
