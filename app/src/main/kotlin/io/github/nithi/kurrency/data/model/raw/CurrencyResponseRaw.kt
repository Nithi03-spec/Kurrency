

package io.github.nithi.kurrency.data.model.raw

import kotlinx.serialization.Serializable

/**
 * A data class that includes list of currencies
 *
 * @property baseCurrency The base currency to calculate amounts
 * @property rates The list of currencies
 */

@Serializable
data class CurrencyResponseRaw(
    val amount: Double = 1.0,  // Add this field with default value
    val base: String,          // or baseCurrency: String - check your existing field name
    val date: String,
    val rates: Map<String, Double>
)
