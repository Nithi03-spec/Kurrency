/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.model.view

import androidx.annotation.DrawableRes

/**
 * A data class that includes each currency for view layer
 *
 * @property id The id for each item
 * @property abbreviation The shortened currency name
 * @property longName The long style of [abbreviation]
 * @property amount The currency amount
 * @property icon The currency icon that represents its country's flag
 */
data class RateViewItem(
    val id: Int,
    val abbreviation: String,
    val longName: String,
    val amount: Float,
    @DrawableRes
    val icon: Int
)
