/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:JvmName("ActivityKt")

package io.github.nithi.kurrency.util.ext

import android.app.Activity
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager

/**
 * Hides keyboard from the UI.
 */
fun Activity.hideKeyboard() {
    val currentFocus = currentFocus
    currentFocus?.windowToken?.let { windowToken ->

        val inputManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}
