/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.verifier

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.github.nithi.kurrency.shared.BASE_URL
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * A test class for [RevolutHostnameVerifier]
 */
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class RevolutHostnameVerifierTest {

    /*
     -----------------------
    |    Private members    |
     -----------------------
    */
    private val revolutHostNameVerifierTest: RevolutHostnameVerifier
        get() = RevolutHostnameVerifier

    @Test
    fun `verifier should verify the base url`() {
        // Given
        val hostname = BASE_URL

        // When
        val result = revolutHostNameVerifierTest.verify(hostname, null)

        // Then
        assertThat(result).isTrue()
    }

    @Test
    fun `verifier should not allow any network traffic except base url`() {
        // Given
        val hostname = null

        // When
        val result = revolutHostNameVerifierTest.verify(hostname, null)

        // Then
        assertThat(result).isFalse()
    }
}
