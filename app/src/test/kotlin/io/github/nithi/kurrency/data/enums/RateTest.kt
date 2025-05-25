/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.enums

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

/**
 * A parameterized test class for [Rate]
 */
@SmallTest
class RateTest {

    /**
     * A parameterized test function that iterates and performs test on all rates.
     *
     * @param rate represents any [Rate]
     */
    @ParameterizedTest
    @EnumSource(Rate::class)
    fun `any rate length should be at least 3`(rate: Rate) {
        assertThat(rate.name).isNotNull()
        // We know all rates 3-digit length except UNKNOWN
        assertThat(rate.name.length).isAtLeast(3)
    }
}
