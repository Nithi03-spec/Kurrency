/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.datasource

import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.raw.CurrencyResponseRaw
import io.github.nithi.kurrency.data.service.CurrencyService
import io.github.nithi.kurrency.util.coroutines.AsyncManager
import io.github.nithi.kurrency.util.coroutines.DefaultAsyncManager
import io.github.nithi.kurrency.util.coroutines.DispatcherProvider
import io.github.nithi.kurrency.util.mapper.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [DataSource] implementation to fetch list of currencies remotely.
 *
 * @param currencyService The service to hit the endpoint
 * @param mapper The domain mapper to map raw data to domain
 * @param dispatcherProvider The [DispatcherProvider] to run calls under a specific context
 */
@Singleton
class CurrencyRemoteDataSource @Inject constructor(
    private val currencyService: CurrencyService,
    private val mapper: @JvmSuppressWildcards Mapper<CurrencyResponseRaw, CurrencyResponse>,
    private val dispatcherProvider: DispatcherProvider
) : DataSource, AsyncManager by DefaultAsyncManager(dispatcherProvider) {

    /**
     * Fetches list of currencies and returns in [Flow] builder
     *
     * @param base The base currency to fetch list
     *
     * @return [CurrencyResponse] within [Flow] builder
     */
    @ExperimentalCoroutinesApi
    override fun getCurrencyList(
        base: Rate
    ): Flow<Result<CurrencyResponse>> {
        return handleAsyncWithTryCatch {
            val response = currencyService.getCurrencyList(base)
            mapper.map(response)
        }
    }
}
