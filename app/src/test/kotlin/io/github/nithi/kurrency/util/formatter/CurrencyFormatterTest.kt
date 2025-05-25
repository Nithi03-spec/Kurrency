/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.formatter

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * A test class for [CurrencyFormatter]
 */
@RunWith(MockitoJUnitRunner::class)
class CurrencyFormatterTest {

    private val formatter: Formatter = CurrencyFormatter()

    @Test
    fun `formatter should format given text properly`() {
        // Given
        val text = "1235"

        // When
        val result = formatter.formatText(text)

        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo("1,235")
    }

    @Test
    fun `formatter should format given number properly`() {
        // Given
        val number = "12345678"

        // When
        val result = formatter.formatText(number)

        // Then
        assertThat(result).isNotEmpty()
        assertThat(result).isEqualTo("12,345,678")
    }

    @Test
    fun `formatter should parse given text properly`() {
        // Given
        val text = "12345"

        // When
        val result = formatter.parseText(text)

        // Then
        assertThat(result).isEqualTo(12345)
    }
}
