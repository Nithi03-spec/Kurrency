/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.ext

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import com.google.android.material.snackbar.Snackbar

/**
 * Show a [Snackbar] on the target view.
 *
 * @param message a String message
 */
fun View.snackBar(message: String) = showSnackbar(this, message)

/**
 * Sets [View]'s visibility to [GONE]
 */
fun View.hide() {
    visibility = GONE
}

/**
 * Sets [View]'s visibility to [VISIBLE]
 */
fun View.show() {
    visibility = VISIBLE
}
