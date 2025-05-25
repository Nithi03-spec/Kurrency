/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:JvmName("FragmentKt")

package io.github.nithi.kurrency.util.ext

import androidx.fragment.app.Fragment
import io.github.nithi.kurrency.KurrencyApplication

/**
 * An inline value that takes [Fragment]'s application as [kurrencyApplication]
 */
inline val Fragment.kurrencyApplication: KurrencyApplication
    get() = (requireActivity().application as KurrencyApplication)

/**
 * Hides keyboard from the UI.
 */
fun Fragment.hideKeyboard() = requireActivity().hideKeyboard()
