/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.binding.adapters

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputEditText
import io.github.nithi.kurrency.binding.di.BindingScope
import io.github.nithi.kurrency.util.formatter.Formatter
import javax.inject.Inject

/**
 * A [BindingAdapter] for views whose can work with text.
 */
@BindingScope
class TextBindingAdapter @Inject constructor(private val formatter: Formatter) {

    /**
     * Formats and binds given number by multiplying with [multiplier].
     *
     * @param amount The amount
     * @param multiplier The multiplier to multiply amount
     */
    @BindingAdapter("android:amount", "android:multiplier", requireAll = true)
    fun TextInputEditText.bindAmount(amount: Float, multiplier: String) {
        val formattedMultiplier = Converter.stringToFloat(multiplier)
        val formattedAmount = formatter.formatNumber(amount * formattedMultiplier)
        setText(formattedAmount)
    }

    /**
     * Formats hint for target TextView
     *
     * @param amount The amount
     */
    @BindingAdapter("android:hint")
    fun TextInputEditText.formatHint(amount: String) {
        val formattedAmount = formatter.formatText(amount)
        hint = formattedAmount
    }
}
