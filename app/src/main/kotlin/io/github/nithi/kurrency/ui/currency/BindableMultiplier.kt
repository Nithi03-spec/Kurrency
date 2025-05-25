/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui.currency

import androidx.annotation.UiThread
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import io.github.nithi.kurrency.BR
import io.github.nithi.kurrency.ui.di.MainScope
import io.github.nithi.kurrency.util.ext.i
import javax.inject.Inject
import kotlin.properties.Delegates

/**
 * A [BaseObservable] implementation that holds multiplier entered by responder view.
 */
@MainScope
class BindableMultiplier @Inject constructor() : BaseObservable() {

    @get:Bindable
    var multiplier: Float by Delegates.observable(DEFAULT_VALUE) { _, _, newValue ->
        notifyPropertyChanged(BR.multiplier)

        i { "Current multiplier is $newValue" }
    }

    @UiThread
    fun reset() {
        multiplier = DEFAULT_VALUE
    }

    private companion object {
        private const val DEFAULT_VALUE = 1.0f
    }
}
