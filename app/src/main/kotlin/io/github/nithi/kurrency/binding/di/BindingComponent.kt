/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.binding.di

import androidx.databinding.DataBindingComponent
import dagger.Subcomponent
import io.github.nithi.kurrency.binding.adapters.ImageBindingAdapter
import io.github.nithi.kurrency.binding.adapters.TextBindingAdapter

@BindingScope
@Subcomponent(modules = [BindingModule::class])
interface BindingComponent : DataBindingComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): BindingComponent
    }

    override fun getImageBindingAdapter(): ImageBindingAdapter

    override fun getTextBindingAdapter(): TextBindingAdapter
}
