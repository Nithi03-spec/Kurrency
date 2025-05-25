/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.mapper

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.raw.CurrencyResponseRaw
import io.github.nithi.kurrency.data.shared.rule.CoroutinesTestRule
import io.github.nithi.kurrency.shared.ext.runBlockingTest
import io.github.nithi.kurrency.util.mapper.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import io.github.nithi.kurrency.data.model.domain.Rate as DomainRate

/**
 * A test class for [CurrencyDomainMapper]
 */
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class CurrencyDomainMapperTest {

    /*
     ------------
    |    Rules   |
     ------------
    */
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    /*
     -----------------------
    |    Private members    |
     -----------------------
    */
    private lateinit var mapper: Mapper<CurrencyResponseRaw, CurrencyResponse>

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        mapper = CurrencyDomainMapper(coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `mapper should map raw data to domain type properly`() =
        coroutinesTestRule.runBlockingTest {
            // Given
            val currencyResponseRaw = CurrencyResponseRaw(
                baseCurrency = "EUR",
                rates = mapOf(Rate.CZK to 1.2f, Rate.AUD to 3.5f)
            )

            // When
            val response = mapper.map(currencyResponseRaw)

            // Then
            assertThat(response).isNotNull()
            assertThat(response.baseCurrency).isEqualTo("EUR")
            assertThat(response.rates).isNotNull()
            assertThat(response.rates).hasSize(2)
            assertThat(response.rates).containsExactlyElementsIn(
                listOf(DomainRate(Rate.CZK, 1.2f), DomainRate(Rate.AUD, 3.5f))
            ).inOrder()
        }
}
