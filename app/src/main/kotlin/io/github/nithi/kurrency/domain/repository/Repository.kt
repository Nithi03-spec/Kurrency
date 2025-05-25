/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.domain.repository

import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.datasource.DataSource
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import kotlinx.coroutines.flow.Flow

/**
 * A helper interface for repository layer to interact with [DataSource]
 */
@FunctionalInterface
interface Repository {

    /**
     * Fetches list of currencies and returns in [Flow] builder
     *
     * @param base The base currency to fetch list
     *
     * @return [CurrencyResponse] within [Flow] builder
     */
    fun getCurrencyList(base: Rate): Flow<Result<CurrencyResponse>>
}
