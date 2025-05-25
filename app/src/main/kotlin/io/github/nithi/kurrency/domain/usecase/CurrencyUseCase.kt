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
package io.github.nithi.kurrency.domain.usecase

import io.github.nithi.kurrency.data.Result
import io.github.nithi.kurrency.data.enums.Rate
import io.github.nithi.kurrency.data.model.domain.CurrencyResponse
import io.github.nithi.kurrency.data.model.view.CurrencyResponseViewItem
import io.github.nithi.kurrency.data.succeeded
import io.github.nithi.kurrency.domain.repository.Repository
import io.github.nithi.kurrency.util.coroutines.DispatcherProvider
import io.github.nithi.kurrency.util.mapper.Mapper
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A [UseCase.FlowUseCase] implementation to interact with [Repository] in order to fetch list of
 * currencies.
 *
 * @param repository The repository
 */
@Singleton
class CurrencyUseCase @Inject constructor(
    private val repository: Repository,
    private val mapper: @JvmSuppressWildcards Mapper<CurrencyResponse, CurrencyResponseViewItem>,
    private val dispatcherProvider: DispatcherProvider
) : UseCase.FlowUseCase<CurrencyParams, CurrencyResponseViewItem> {

    /**
     * Executes the call with the given parameters.
     *
     * @param params The [CurrencyParams] to fetch list
     *
     * @return [CurrencyResponse] within [Flow] builder
     */
    @ExperimentalCoroutinesApi
    override fun execute(params: CurrencyParams): Flow<Result<CurrencyResponseViewItem>> {
        return repository.getCurrencyList(params.base)
            .flatMapLatest { result ->
                flow {
                    if (result.succeeded) {
                        result as Result.Success
                        val viewItem = mapper.map(result.data)
                        emit(Result.Success(viewItem))
                        return@flow
                    }
                    result as Result.Error
                    emit(Result.Error(result.failure))
                }
            }.flowOn(dispatcherProvider.default)
    }
}

/**
 * The data class to fetch list with base currency
 *
 * @property base The base currency
 */
data class CurrencyParams(
    val base: Rate = Rate.EUR
) : Params()
