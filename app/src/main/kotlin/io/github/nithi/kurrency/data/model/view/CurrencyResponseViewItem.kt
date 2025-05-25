/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.model.view

/**
 * A data class that includes list of currencies for view layer
 *
 * @property baseCurrency The base currency to calculate amounts
 * @property rates The list of currencies
 */
data class CurrencyResponseViewItem(
    val baseCurrency: String,
    val rates: List<RateViewItem>
)
