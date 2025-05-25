/*
 * Copyright (C) 2020. Nuh Koca. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
