/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.model.domain

/**
 * A data class that includes list of currencies for domain layer
 *
 * @property baseCurrency The base currency to calculate amounts
 * @property rates The list of currencies
 */
data class CurrencyResponse(
    val baseCurrency: String,
    val rates: List<Rate>
)
