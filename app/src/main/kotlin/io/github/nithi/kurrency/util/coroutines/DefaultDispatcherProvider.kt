/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Coroutines dispatcher implementation for [Dispatchers]. Processes will be started inside the
 * specified thread by this class.
 */
@Singleton
internal class DefaultDispatcherProvider @Inject constructor() : DispatcherProvider {

    /**
     * Binds [Dispatchers.Main] by default.
     */
    override val main: CoroutineDispatcher
        get() = Dispatchers.Main

    /**
     * Binds [Dispatchers.Default] by default.
     */
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default

    /**
     * Binds [Dispatchers.IO] by default.
     */
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO

    /**
     * Binds [Dispatchers.Unconfined] by default.
     */
    @ExperimentalCoroutinesApi
    override val unconfined: CoroutineDispatcher
        get() = Dispatchers.Unconfined
}
