/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.coroutines

import androidx.test.filters.SmallTest
import com.google.common.truth.Truth.assertThat
import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.failure.Failure
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.shared.rule.CoroutinesTestRule
import io.github.nithi.kurrency.shared.assertion.test
import io.github.nithi.kurrency.shared.ext.runBlockingTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import io.github.nithi.kurrency.data.model.domain.Rate as DomainRate

/**
 * A test class for [DefaultAsyncManager]
 */
@RunWith(MockitoJUnitRunner::class)
@SmallTest
class DefaultAsyncManagerTest {

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
    private lateinit var asyncManager: AsyncManager

    @Before
    @ExperimentalCoroutinesApi
    fun setUp() {
        asyncManager = DefaultAsyncManager(coroutinesTestRule.testDispatcherProvider)
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `async manager should return data successfully`() = coroutinesTestRule.runBlockingTest {
        // Given
        val mockedResponse = CurrencyResponse(
            baseCurrency = "EUR",
            rates = listOf(DomainRate(Rate.GBP, 1.1f), DomainRate(Rate.RUB, 4.1f))
        )

        // When
        val flow =
            asyncManager.handleAsyncWithTryCatch { mockedResponse }

        // Then
        flow.test {
            expectItem().run {
                assertThat(this).isInstanceOf(Result.Success::class.java)
                this as Result.Success
                assertThat(data).isNotNull()
                assertThat(data.baseCurrency).isEqualTo("EUR")
                assertThat(data.rates).hasSize(2)
                assertThat(data.rates).containsExactly(
                    DomainRate(Rate.GBP, 1.1f), DomainRate(Rate.RUB, 4.1f)
                ).inOrder()
            }
            cancel()
            expectNoMoreEvents()
        }
    }

    @Test
    @ExperimentalCoroutinesApi
    fun `async manager should handle exception properly`() = coroutinesTestRule.runBlockingTest {
        // Given
        val exception = Failure.ServerFailure("Couldn't connect the server.")

        // When
        val flow = asyncManager.handleAsyncWithTryCatch { throw exception }

        // Then
        flow.test {
            expectItem().run {
                assertThat(this).isInstanceOf(Result.Error::class.java)
                this as Result.Error
                assertThat(failure).isInstanceOf(Failure.ServerFailure::class.java)
                assertThat(failure.message).isEqualTo("Couldn't connect the server.")
            }
            cancelAndIgnoreRemainingEvents()
        }
    }
}
