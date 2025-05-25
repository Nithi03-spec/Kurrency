/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.domain.mapper

import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.view.CurrencyResponseViewItem
import io.github.nithi.kurrency.data.model.view.RateViewItem
import io.github.nithi.kurrency.util.coroutines.DispatcherProvider
import io.github.nithi.kurrency.util.mapper.Mapper
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [Mapper] implementation to map [CurrencyResponse] to [CurrencyResponseViewItem] type.
 *
 * @param dispatcherProvider The [DispatcherProvider] to run calls under a specific context
 */
@Singleton
class CurrencyViewItemMapper @Inject constructor(
    private val dispatcherProvider: DispatcherProvider
) : Mapper<CurrencyResponse, CurrencyResponseViewItem> {

    /**
     * A suspend function that maps [CurrencyResponse] to [CurrencyResponseViewItem] type.
     *
     * @param item The [CurrencyResponse]
     *
     * @return [CurrencyResponseViewItem]
     */
    override suspend fun map(item: CurrencyResponse) = withContext(dispatcherProvider.default) {
        val rates = mutableListOf<RateViewItem>()

        rates with item.baseCurrency

        item.rates.forEachIndexed { index, currency ->
            val rateViewItem = RateViewItem(
                id = index + 1,
                abbreviation = currency.rate.name,
                longName = currency.rate.longName,
                amount = currency.amount,
                icon = currency.rate.resId
            )
            rates.add(index = index + 1, element = rateViewItem)
        }

        CurrencyResponseViewItem(baseCurrency = item.baseCurrency, rates = rates)
    }
}

/**
 * An infix fun that adds responder to currency list to show it at top.
 *
 * @param baseCurrency represents responder
 */
private infix fun MutableList<RateViewItem>.with(baseCurrency: String) {
    // Add base currency to currency list to show it at top.
    val rate = Rate.valueOf(baseCurrency)
    val responder = RateViewItem(
        id = 0, // First id should always be 0
        abbreviation = rate.name,
        longName = rate.longName,
        amount = 1.0f, // Because base currency amount is always 1
        icon = rate.resId
    )
    add(index = 0, element = responder)
}
