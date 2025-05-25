/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.domain.mapper

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.github.nithi.kurrency.R
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.view.CurrencyResponseViewItem
import io.github.nithi.kurrency.data.model.view.RateViewItem
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
 * A test class for [CurrencyViewItemMapper]
 */
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class CurrencyViewItemMapperTest {

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
    private lateinit var mapper: Mapper<CurrencyResponse, CurrencyResponseViewItem>

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        mapper = CurrencyViewItemMapper(coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `mapper should map domain data to view item type properly`() =
        coroutinesTestRule.runBlockingTest {
            // Given
            val currencyResponse = CurrencyResponse(
                baseCurrency = "EUR",
                rates = listOf(DomainRate(Rate.BGN, 3.9f), DomainRate(Rate.CHF, 2.1f))
            )

            // When
            val response = mapper.map(currencyResponse)

            // Then
            assertThat(response).isNotNull()
            assertThat(response.baseCurrency).isEqualTo("EUR")
            assertThat(response.rates).isNotNull()
            assertThat(response.rates).hasSize(3)
            assertThat(response.rates).containsExactlyElementsIn(
                listOf(
                    RateViewItem(
                        id = 0,
                        abbreviation = "EUR",
                        longName = "Euro",
                        amount = 1.0f,
                        icon = R.drawable.ic_eur
                    ),
                    RateViewItem(
                        id = 1,
                        abbreviation = "BGN",
                        longName = "Bulgarian Lev",
                        amount = 3.9f,
                        icon = R.drawable.ic_bgn
                    ),
                    RateViewItem(
                        id = 2,
                        abbreviation = "CHF",
                        longName = "Swiss Franc",
                        amount = 2.1f,
                        icon = R.drawable.ic_chf
                    )
                )
            ).inOrder()
        }
}
