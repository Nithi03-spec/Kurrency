/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.domain.repository

import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.datasource.DataSource
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.di.Remote
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [Repository] implementation to interact with [DataSource] in order to fetch list of
 * currencies.
 *
 * @param remoteDataSource The data source
 */
@Singleton
class CurrencyRepository @Inject constructor(
    @Remote private val remoteDataSource: DataSource
) : Repository {

    /**
     * Fetches list of currencies and returns in [Flow] builder
     *
     * @param base The base currency to fetch list
     *
     * @return [CurrencyResponse] within [Flow] builder
     */
    override fun getCurrencyList(base: Rate): Flow<Result<CurrencyResponse>> {
        return remoteDataSource.getCurrencyList(base)
    }
}
