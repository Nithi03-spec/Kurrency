/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.binding.di

import android.content.Context
import coil.ImageLoader
import coil.memory.MemoryCache
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.github.nithi.kurrency.binding.adapters.ImageBindingAdapter
import io.github.nithi.kurrency.binding.adapters.TextBindingAdapter
import io.github.nithi.kurrency.util.formatter.CurrencyFormatter
import io.github.nithi.kurrency.util.formatter.Formatter
import javax.inject.Qualifier
import javax.inject.Scope

@Module
internal abstract class BindingModule {

    @Binds
    @BindingScope
    internal abstract fun provideFormatter(currencyFormatter: CurrencyFormatter): Formatter

    @Module
    internal companion object {

        private const val DEFAULT_MEMORY_MULTIPLIER = 0.5

        @Provides
        @BindingScope
        internal fun provideImageLoader(context: Context) =
            ImageLoader(context).newBuilder().apply {
                memoryCache {
                    MemoryCache.Builder(context)
                        .maxSizePercent(DEFAULT_MEMORY_MULTIPLIER)
                        .build()
                }
                crossfade(true)
            }.build()

        @Provides
        @InternalApi
        @BindingScope
        internal fun provideImageBindingAdapter(
            imageLoader: ImageLoader
        ) = ImageBindingAdapter(imageLoader)

        @Provides
        @InternalApi
        @BindingScope
        internal fun provideTextBindingAdapter(formatter: Formatter) = TextBindingAdapter(formatter)
    }
}

@Scope
@MustBeDocumented
internal annotation class BindingScope

@Qualifier
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
private annotation class InternalApi
