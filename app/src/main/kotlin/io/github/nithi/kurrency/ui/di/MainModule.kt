/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui.di

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import io.github.nithi.kurrency.ui.currency.BindableMultiplier
import io.github.nithi.kurrency.di.factory.FragmentKey
import io.github.nithi.kurrency.di.factory.ViewModelKey
import io.github.nithi.kurrency.ui.currency.CurrencyAdapter
import io.github.nithi.kurrency.ui.currency.CurrencyFragment
import io.github.nithi.kurrency.ui.currency.CurrencyViewModel
import io.github.nithi.kurrency.util.event.SingleLiveEvent
import javax.inject.Scope

@Module
internal abstract class MainModule {

    @Binds
    @IntoMap
    @MainScope
    @FragmentKey(CurrencyFragment::class)
    internal abstract fun bindCurrencyFragment(currencyFragment: CurrencyFragment): Fragment

    @Binds
    @IntoMap
    @MainScope
    @ViewModelKey(CurrencyViewModel::class)
    internal abstract fun bindCurrencyViewModel(viewModel: CurrencyViewModel): ViewModel

    @Module
    internal companion object {

        @Provides
        @MainScope
        internal fun provideCurrencyAdapter(
            itemClickListener: SingleLiveEvent<String>,
            bindableMultiplier: BindableMultiplier
        ) = CurrencyAdapter(itemClickListener, bindableMultiplier)

        @Provides
        @MainScope
        internal fun provideItemClickListener() = SingleLiveEvent<String>()
    }
}

@Scope
@MustBeDocumented
internal annotation class MainScope
