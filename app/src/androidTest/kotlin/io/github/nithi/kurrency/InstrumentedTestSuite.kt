/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency

import io.github.nithi.kurrency.ui.MainActivityTest
import io.github.nithi.kurrency.ui.currency.CurrencyFragmentTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * An instrumented test suite to execute all the test classes under this module.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CurrencyFragmentTest::class,
    MainActivityTest::class
)
object InstrumentedTestSuite
