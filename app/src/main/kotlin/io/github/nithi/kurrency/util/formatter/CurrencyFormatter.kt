/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.formatter

import io.github.nithi.kurrency.binding.di.BindingScope
import java.text.ParseException
import javax.inject.Inject

/**
 * The [Formatter] implementation.
 */
@BindingScope
class CurrencyFormatter @Inject constructor() : Formatter {

    override fun formatText(text: String): String {
        val number = parseText(text)
        return formatNumber(number)
    }

    override fun formatNumber(number: Number?): String {
        return try {
            formatter.format(number)
        } catch (e: IllegalArgumentException) {
            ""
        }
    }

    @Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
    override fun parseText(text: String): Number {
        return try {
            formatter.parse(text)
        } catch (e: ParseException) {
            0.0f
        }
    }
}
