/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.failure

import kotlinx.serialization.Serializable

/**
 * A data class which represents error response.
 *
 * @property message The error message
 */
@Serializable
internal data class ErrorResponse(
    val message: String
)
