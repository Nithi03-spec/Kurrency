/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.coroutines

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

/**
 * A main interface that manages Coroutines contex. This class also provides a crucial benefit for
 * testing.
 */
interface DispatcherProvider {

    /**
     * Dispatcher definition for [Dispatchers.Main]
     */
    val main: CoroutineDispatcher

    /**
     * Dispatcher definition for [Dispatchers.Default]. This thread should be used for heavy background
     * processes.
     */
    val default: CoroutineDispatcher

    /**
     * Dispatcher definition for [Dispatchers.IO]. This thread should be used for IO processes.
     */
    val io: CoroutineDispatcher

    /**
     * Dispatcher definition for [Dispatchers.Unconfined]
     */
    val unconfined: CoroutineDispatcher
}
