/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.serializers

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.github.nithi.kurrency.data.enums.Rate
import kotlinx.serialization.json.Json
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * A test class for [RateSerializer]
 */
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class RateSerializerTest {

    /*
     -----------------------
    |    Private members    |
     -----------------------
    */
    private val serializer = RateSerializer

    @Test
    fun `serializer should serialize the given enum object properly`() {
        // Given
        val rate = Rate.BRL

        // When
        val rateAsString = Json.encodeToString(serializer, rate)

        // Then
        assertThat(rateAsString).isNotEmpty()
        assertThat(rateAsString).isEqualTo("\"${rate.name}\"")
    }

    @Test
    fun `serializer should parse the given string to corresponding enum type`() {
        // Given
        val rateAsString = "CZK"

        // When
        val rate = Json { isLenient = true }.decodeFromString(serializer, rateAsString)

        // Then
        assertThat(rate).isNotNull()
        assertThat(rate).isInstanceOf(Rate::class.java)
        assertThat(rate).isEqualTo(Rate.CZK)
    }

    @Test
    fun `serializer should map undefined item to UNKNOWN type`() {
        // Given
        val rateAsString = "UND"

        // When
        val rate = Json { isLenient = true }.decodeFromString(serializer, rateAsString)

        // Then
        assertThat(rate).isNotNull()
        assertThat(rate).isInstanceOf(Rate::class.java)
        assertThat(rate).isEqualTo(Rate.UNKNOWN)
    }
}
