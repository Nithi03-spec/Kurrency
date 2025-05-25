/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.data.service

import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.raw.CurrencyResponseRaw
import retrofit2.http.GET
import retrofit2.http.Query
private const val BASE_URL = "https://api.frankfurter.app/"

/**
 * The service interface to fetch list of currencies
 */
interface CurrencyService {

    /**
     * Fetches list of currencies
     *
     * @param base The base currency to calculate amounts
     *
     * @return [CurrencyResponseRaw]
     */
    @GET("latest")
    suspend fun getCurrencyList(@Query("base") base: Rate): CurrencyResponseRaw

    //private companion object {
      //  private const val ENDPOINT_PREFIX = "api/android"
    //}
}
