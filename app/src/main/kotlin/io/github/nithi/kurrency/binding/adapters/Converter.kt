package io.github.nithi.kurrency.binding.adapters

import androidx.databinding.InverseMethod
import io.github.nithi.kurrency.util.formatter.CurrencyFormatter
import io.github.nithi.kurrency.util.formatter.Formatter

/**
 * A DataBinding converter between Float and String to obtain current multiplier.
 */
object Converter {

    private val formatter: Formatter = CurrencyFormatter()

    @InverseMethod("stringToFloat")
    @JvmStatic
    fun floatToString(value: Float): String {
        return formatter.formatNumber(value)
    }

    @JvmStatic
    fun stringToFloat(value: String): Float {
        val number = formatter.parseText(value)
        return number.toFloat()
    }
}
