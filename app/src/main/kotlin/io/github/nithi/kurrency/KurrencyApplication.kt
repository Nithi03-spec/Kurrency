/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency

import android.app.Application
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ProcessLifecycleOwner
import io.github.nithi.kurrency.BuildConfig.DEBUG
import io.github.nithi.kurrency.di.AppComponent
import io.github.nithi.kurrency.di.DaggerAppComponent
import timber.log.Timber
import timber.log.Timber.DebugTree

/**
 * An application that initializes Dagger and lazily provides [AppComponent].
 *
 * Also, sets up Timber in the DEBUG BuildConfig.
 */
class KurrencyApplication : Application() {

    private val processLifecycleOwner = ProcessLifecycleOwner.get()

    // Instance of the AppComponent that will be used by all the Activities in the project
    val appComponent: AppComponent by lazy {
        initializeComponent()
    }

    private fun initializeComponent(): AppComponent {
        // Creates an instance of AppComponent using its Factory constructor
        // We pass the applicationContext that will be used as Context in the graph
        return DaggerAppComponent.factory().create(applicationContext).also { appComponent ->
            val bindingComponent = appComponent.dataBindingComponent().create()
            DataBindingUtil.setDefaultComponent(bindingComponent)
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (DEBUG) Timber.plant(DebugTree())
        processLifecycleOwner.lifecycle.addObserver(ApplicationObserver())
    }
}
