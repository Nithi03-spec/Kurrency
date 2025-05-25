/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:JvmName("SnackbarKt")

package io.github.nithi.kurrency.util.ext

import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.updateMargins
import com.google.android.material.snackbar.Snackbar

/**
 * An extension file for [Snackbar]
 */
private const val SNACKBAR_MAX_LINES_DEFAULT = 3

/**
 * Fixes [Snackbar]'s view at a fixed size especially while in fullscreen mode.
 */
private fun View.setFixedSize() {
    // Have Snackbar at fixed size when display is fullscreen
    setOnApplyWindowInsetsListener { v, insets ->
        v.setPadding(v.paddingLeft, v.paddingTop, v.paddingRight, v.paddingTop)

        val params = v.layoutParams as ViewGroup.MarginLayoutParams
        params.updateMargins(
            params.leftMargin,
            params.topMargin,
            params.rightMargin,
            params.bottomMargin + insets.systemWindowInsetBottom
        )
        v.layoutParams = params

        insets
    }
}

/**
 * Show snackbar with a String message
 *
 * @param view target
 * @param message a String message
 */
fun showSnackbar(view: View, message: String) {
    val trimmedMessage = message.trimStart().trimEnd()
    val snackbar = Snackbar.make(view, trimmedMessage, Snackbar.LENGTH_LONG)
    val snackbarView = snackbar.view

    val textView =
        snackbarView.findViewById(com.google.android.material.R.id.snackbar_text) as TextView
    textView.maxLines = SNACKBAR_MAX_LINES_DEFAULT

    // Have Snackbar at fixed size when display is fullscreen
    snackbarView.setFixedSize()
    snackbar.show()
}
