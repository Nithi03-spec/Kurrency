/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui.di

import dagger.Subcomponent
import io.github.nithi.kurrency.ui.NavHostFragment

@MainScope
@Subcomponent(modules = [MainModule::class])
interface MainComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): MainComponent
    }

    fun inject(navHostFragment: NavHostFragment)
}
