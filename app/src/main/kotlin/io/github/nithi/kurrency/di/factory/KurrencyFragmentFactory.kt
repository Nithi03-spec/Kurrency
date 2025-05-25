/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.di.factory

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import javax.inject.Inject
import javax.inject.Provider
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FUNCTION
import kotlin.annotation.AnnotationTarget.PROPERTY_GETTER
import kotlin.annotation.AnnotationTarget.PROPERTY_SETTER
import kotlin.reflect.KClass

/**
 * FragmentFactory which uses Dagger to create the instances.
 */
@Suppress("TooGenericExceptionCaught", "TooGenericExceptionThrown")
class kurrencyFragmentFactory @Inject constructor(
    private val creators: Map<Class<out Fragment>, @JvmSuppressWildcards Provider<Fragment>>
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        val fragmentClass = loadFragmentClass(classLoader, className)

        val creator = creators[fragmentClass] ?: return super.instantiate(classLoader, className)

        return try {
            creator.get()
        } catch (e: RuntimeException) {
            throw RuntimeException(e)
        }
    }
}

@Module
internal abstract class FragmentBindingsModule {

    @Binds
    internal abstract fun bindFragmentFactory(
        fragmentFactory: kurrencyFragmentFactory
    ): FragmentFactory
}

@MapKey
@Retention(RUNTIME)
@Target(FUNCTION, PROPERTY_GETTER, PROPERTY_SETTER)
internal annotation class FragmentKey(val value: KClass<out Fragment>)
