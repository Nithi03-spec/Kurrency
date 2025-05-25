/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:JvmName("TimberKt")

package io.github.nithi.kurrency.util.ext

import android.annotation.SuppressLint
import timber.log.Timber

/**
 * Extension function that wraps [Timber]'s debug logging.
 *
 * @param message The message block
 */
@SuppressLint("TimberLogDetector")
inline fun d(crossinline message: () -> String) = log { Timber.d(message()) }

/**
 * Extension function that wraps [Timber]'s info logging.
 *
 * @param message The message block
 */
@SuppressLint("TimberLogDetector")
inline fun i(crossinline message: () -> String) = log { Timber.i(message()) }

/**
 * Extension function that wraps [Timber]'s warning logging.
 *
 * @param message The message block
 */
@SuppressLint("TimberLogDetector")
inline fun w(crossinline message: () -> String) = log { Timber.w(message()) }

/**
 * Extension function that wraps [Timber]'s error logging.
 *
 * @param message The message block
 */
@SuppressLint("TimberLogDetector")
inline fun e(crossinline message: () -> String) = log { Timber.e(message()) }

/** @suppress */
@PublishedApi
internal inline fun log(block: () -> Unit) {
    if (Timber.treeCount > 0) block()
}
