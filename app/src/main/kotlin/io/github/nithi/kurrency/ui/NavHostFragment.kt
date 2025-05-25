/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import io.github.nithi.kurrency.di.factory.KurrencyFragmentFactory
import io.github.nithi.kurrency.ui.di.MainScope
import io.github.nithi.kurrency.util.ext.kurrencyApplication
import javax.inject.Inject

/**
 * A custom [NavHostFragment] to allow [kurrencyFragmentFactory] to inflate fragments using this host.
 */
@MainScope
class NavHostFragment : NavHostFragment() {

    @Inject
    lateinit var fragmentFactory: FragmentFactory

    override fun onAttach(context: Context) {
        kurrencyApplication.appComponent.mainComponent().create().inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        childFragmentManager.fragmentFactory = fragmentFactory
        super.onCreate(savedInstanceState)
    }
}
