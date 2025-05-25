/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import io.github.nithi.kurrency.binding.di.BindingComponent
import io.github.nithi.kurrency.di.factory.FragmentBindingsModule
import io.github.nithi.kurrency.di.factory.ViewModelBuilderModule
import io.github.nithi.kurrency.ui.di.MainComponent
import javax.inject.Singleton

/**
 * The main component to build Dagger graph.
 */
@Singleton
@Component(
    modules = [
        AppModule::class,
        SubcomponentsModule::class,
        FragmentBindingsModule::class,
        ViewModelBuilderModule::class
    ]
)
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun dataBindingComponent(): BindingComponent.Factory

    fun mainComponent(): MainComponent.Factory
}

@Module(
    subcomponents = [
        BindingComponent::class,
        MainComponent::class
    ]
)
object SubcomponentsModule
