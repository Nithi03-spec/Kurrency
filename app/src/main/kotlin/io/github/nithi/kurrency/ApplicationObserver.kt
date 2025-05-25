/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import io.github.nithi.kurrency.util.ext.i

/**
 * A [LifecycleObserver] demonstration that just notifies application state.
 */
class ApplicationObserver : LifecycleObserver {

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onBackground() {
        i { "App is in the background" }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onForeground() {
        i { "App is in the foreground" }
    }
}
