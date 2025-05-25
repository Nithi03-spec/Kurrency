/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.mapper

/**
 * An interface to map given input to desired output.
 */
@FunctionalInterface
interface Mapper<in T, out R> {

    /**
     * A suspend function that maps given input to desired output.
     *
     * @param item The input
     *
     * @return [R]
     */
    suspend fun map(item: T): R
}
