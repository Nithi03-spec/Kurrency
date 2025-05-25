package io.github.nithi.rules

import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * A unit test suite to execute all the test classes under this module.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    TimberLogDetectorTest::class
)
object UnitTestSuite
