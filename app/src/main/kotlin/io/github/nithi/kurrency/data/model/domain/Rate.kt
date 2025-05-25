/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.model.domain

import io.github.nithi.kurrency.data.enums.Rate

/**
 * A data class that includes each currency for domain layer
 *
 * @property rate The currency
 * @property amount The currency amount
 */
data class Rate(
    val rate: Rate,
    val amount: Float
)
