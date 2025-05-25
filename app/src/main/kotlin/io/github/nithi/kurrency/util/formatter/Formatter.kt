/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.formatter

import java.text.NumberFormat
import java.text.ParseException
import java.util.*

/**
 * A currency formatter interface that parses and formats any object.
 */
interface Formatter {

    /**
     * Formats given string to desired currency format
     *
     * @param text The text to be formatted
     *
     * @return [Number]
     */
    fun formatText(text: String): String

    /**
     * Formats given number to desired string
     *
     * @param number Any number to be formatted
     *
     * @return [Number]
     *
     * @throws IllegalArgumentException if the Format cannot format the given string
     */
    @Throws(IllegalArgumentException::class)
    fun formatNumber(number: Number?): String

    /**
     * Parses the given string to desired number
     *
     * @param text The string to be parsed
     *
     * @return [String]
     *
     * @throws ParseException if the beginning of the specified string cannot be parsed
     */
    @Throws(ParseException::class)
    fun parseText(text: String): Number

    /**
     * The default formatter
     */
    val formatter: NumberFormat
        get() = NumberFormat.getInstance(Locale.getDefault())
}
