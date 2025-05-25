/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.nithi.kurrency.data.datasource.CurrencyRemoteDataSource
import io.github.nithi.kurrency.data.datasource.DataSource
import io.github.nithi.kurrency.data.mapper.CurrencyDomainMapper
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.raw.CurrencyResponseRaw
import io.github.nithi.kurrency.data.model.view.CurrencyResponseViewItem
import io.github.nithi.kurrency.data.service.CurrencyService
import io.github.nithi.kurrency.domain.mapper.CurrencyViewItemMapper
import io.github.nithi.kurrency.domain.repository.CurrencyRepository
import io.github.nithi.kurrency.domain.repository.Repository
import io.github.nithi.kurrency.domain.usecase.CurrencyParams
import io.github.nithi.kurrency.domain.usecase.CurrencyUseCase
import io.github.nithi.kurrency.domain.usecase.UseCase
import io.github.nithi.kurrency.util.coroutines.DefaultDispatcherProvider
import io.github.nithi.kurrency.util.coroutines.DispatcherProvider
import io.github.nithi.kurrency.util.mapper.Mapper
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    internal abstract fun bindDispatcherProvider(
        defaultDispatcherProvider: DefaultDispatcherProvider
    ): DispatcherProvider

    @Binds
    @Remote
    @Singleton
    internal abstract fun bindCurrencyDataSource(
        currencyRemoteDataSource: CurrencyRemoteDataSource
    ): DataSource

    @Binds
    @Singleton
    internal abstract fun bindCurrencyDomainMapper(
        currencyDomainMapper: CurrencyDomainMapper
    ): Mapper<CurrencyResponseRaw, CurrencyResponse>

    @Binds
    @Singleton
    internal abstract fun bindCurrencyViewItemMapper(
        currencyViewItemMapper: CurrencyViewItemMapper
    ): Mapper<CurrencyResponse, CurrencyResponseViewItem>

    @Binds
    @Singleton
    internal abstract fun bindCurrencyRepository(
        currencyRepository: CurrencyRepository
    ): Repository

    @Binds
    @Singleton
    internal abstract fun bindCurrencyUseCase(
        currencyUseCase: CurrencyUseCase
    ): UseCase.FlowUseCase<CurrencyParams, CurrencyResponseViewItem>

    @Module
    internal companion object {

        private const val MEDIA_TYPE_DEFAULT = "application/json"
        private const val TIMEOUT_IN_MS = 10000L
        private const val BASE_URL = "https://api.frankfurter.app/"

        @Provides
        @Singleton
        internal fun provideCurrencyService(retrofit: Retrofit): CurrencyService = retrofit.create()

        @Provides
        @Singleton
        @OptIn(ExperimentalSerializationApi::class)
        internal fun provideRetrofit(@InternalApi httpClient: OkHttpClient): Retrofit {
            val json = Json {
                ignoreUnknownKeys = true
            }
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(Json.asConverterFactory(MEDIA_TYPE_DEFAULT.toMediaType()))
                .client(httpClient)
                .build()
        }

        @Provides
        @Singleton
        @InternalApi
        internal fun provideOkHttpClient(
            @InternalApi loggingInterceptor: HttpLoggingInterceptor
        ): OkHttpClient {
            return OkHttpClient.Builder()
                .connectTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .writeTimeout(TIMEOUT_IN_MS, TimeUnit.MILLISECONDS)
                .addInterceptor(loggingInterceptor)
                //  .addInterceptor(errorInterceptor())
                .build()
        }

        @Provides
        @Singleton
        @InternalApi
        internal fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
            return HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        }
    }
}

    @Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
private annotation class InternalApi

@Qualifier
@MustBeDocumented
annotation class Remote
