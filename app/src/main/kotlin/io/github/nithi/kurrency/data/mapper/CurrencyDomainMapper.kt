/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.mapper

import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.domain.Rate
import io.github.nithi.kurrency.data.model.raw.CurrencyResponseRaw
import io.github.nithi.kurrency.util.coroutines.DispatcherProvider
import io.github.nithi.kurrency.util.mapper.Mapper
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [Mapper] implementation to map [CurrencyResponseRaw] to [CurrencyResponse] type.
 *
 * @param dispatcherProvider The [DispatcherProvider] to run calls under a specific context
 */
@Singleton
class CurrencyDomainMapper @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : Mapper<CurrencyResponseRaw, CurrencyResponse> {

    /**
     * A suspend function that maps [CurrencyResponseRaw] to [CurrencyResponse] type.
     *
     * @param item The [CurrencyResponseRaw]
     *
     * @return [CurrencyResponse]
     */
    override suspend fun map(item: CurrencyResponseRaw) = withContext(dispatcherProvider.default) {
        val rates = mutableListOf<Rate>()

        item.rates.forEach { currency ->
            try {
                val rateEnum = io.github.nithi.kurrency.data.enums.Rate.valueOf(currency.key)
                val rate = Rate(rateEnum, currency.value.toFloat())
                rates.add(rate)
            } catch (e: IllegalArgumentException) {
                // Handle unknown currency codes gracefully
                // You might want to log this or skip unknown currencies
            }
        }

        // Fixed: Use 'base' instead of 'baseCurrency'
        CurrencyResponse(item.base, rates)
    }
}
