/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency

import io.github.nithi.kurrency.data.datasource.CurrencyRemoteDataSourceTest
import io.github.nithi.kurrency.data.mapper.CurrencyDomainMapperTest
import io.github.nithi.kurrency.data.serializers.RateSerializerTest
import io.github.nithi.kurrency.data.service.CurrencyServiceTest
import io.github.nithi.kurrency.data.verifier.RevolutHostnameVerifierTest
import io.github.nithi.kurrency.domain.mapper.CurrencyViewItemMapperTest
import io.github.nithi.kurrency.domain.repository.CurrencyRepositoryTest
import io.github.nithi.kurrency.domain.usecase.CurrencyUseCaseTest
import io.github.nithi.kurrency.ui.currency.CurrencyViewModelTest
import io.github.nithi.kurrency.util.coroutines.DefaultAsyncManagerTest
import io.github.nithi.kurrency.util.formatter.CurrencyFormatterTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

/**
 * A unit test suite to execute all the test classes under this module.
 */
@RunWith(Suite::class)
@Suite.SuiteClasses(
    CurrencyRemoteDataSourceTest::class,
    CurrencyDomainMapperTest::class,
    RateSerializerTest::class,
    CurrencyServiceTest::class,
    RevolutHostnameVerifierTest::class,
    CurrencyViewItemMapperTest::class,
    CurrencyRepositoryTest::class,
    CurrencyUseCaseTest::class,
    CurrencyViewModelTest::class,
    DefaultAsyncManagerTest::class,
    CurrencyFormatterTest::class
)
object UnitTestSuite
