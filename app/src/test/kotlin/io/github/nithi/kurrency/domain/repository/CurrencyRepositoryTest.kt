/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.domain.repository

import BaseTestClass
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth
import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.datasource.DataSource
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.shared.rule.CoroutinesTestRule
import io.github.nithi.kurrency.shared.assertion.test
import io.github.nithi.kurrency.shared.ext.runBlockingTest
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.slot
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

/**
 * A test class for [CurrencyRepository]
 */
@RunWith(MockitoJUnitRunner::class)
@MediumTest
class CurrencyRepositoryTest : BaseTestClass() {

    /*
     ------------
    |    Rules   |
     ------------
    */
    @ExperimentalCoroutinesApi
    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    /*
     -------------
    |    Mocks    |
     -------------
    */
    @MockK
    private lateinit var dataSource: DataSource

    @RelaxedMockK
    private lateinit var currencyResponse: CurrencyResponse

    /*
     -----------------------
    |    Private members    |
     -----------------------
    */
    private lateinit var repository: Repository
    private val currencySlot = slot<Rate>()

    override fun setUp() {
        super.setUp()

        every { dataSource.getCurrencyList(capture(currencySlot)) } answers {
            flowOf(Result.Success(currencyResponse))
        }

        every { currencyResponse.baseCurrency } returns "THB"

        repository = CurrencyRepository(dataSource)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `repository should return data`() = coroutinesTestRule.runBlockingTest {
        // Given
        val base = Rate.THB

        // When
        val flow = repository.getCurrencyList(base)

        // Then
        flow.test {
            expectItem().run {
                Truth.assertThat(this).isNotNull()
                Truth.assertThat(this).isInstanceOf(Result.Success::class.java)
                this as Result.Success
                Truth.assertThat(data.baseCurrency).isEqualTo(currencySlot.captured.name)
                Truth.assertThat(data.rates).isNotNull()
            }
            expectComplete()
        }

        verify(exactly = 1) { dataSource.getCurrencyList(any()) }
        confirmVerified(dataSource)
    }
}
