/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:JvmName("RecyclerViewKt")

package io.github.nithi.kurrency.util.ext

import android.util.DisplayMetrics
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import io.github.nithi.kurrency.util.delegates.LifecycleAwareScrollListener
import io.github.nithi.kurrency.util.delegates.LifecycleAwareTouchListener

private const val MILLISECONDS_PER_INCH = 0.5f

/**
 * Adds lifecycle aware touch listener to target [Fragment].
 *
 * @param fragment The target fragment
 */
fun RecyclerView.addLifecycleAwareTouchListener(fragment: Fragment) {
    LifecycleAwareTouchListener(fragment, this)
}

/**
 * Adds lifecycle aware scroll listener to target [Fragment].
 *
 * @param fragment The target fragment
 * @param onStateChanged The callback that holds RecyclerView scroll state
 */
fun RecyclerView.addLifecycleAwareScrollListener(
    fragment: Fragment,
    onStateChanged: (newState: Int) -> Unit
) = LifecycleAwareScrollListener(fragment, this, onStateChanged)

/**
 * Adds a very smooth scrolling to [RecyclerView]
 *
 * @param position The target position to scroll
 * @param snapMode The scroll mode
 * @param commitCallback The callback
 */
fun RecyclerView.smoothSnapToPosition(
    position: Int,
    snapMode: Int = LinearSmoothScroller.SNAP_TO_START,
    commitCallback: () -> Unit = {}
) {
    val smoothScroller = object : LinearSmoothScroller(context) {
        override fun getVerticalSnapPreference(): Int = snapMode
        override fun getHorizontalSnapPreference(): Int = snapMode
        override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
            return MILLISECONDS_PER_INCH / displayMetrics.density
        }
    }
    smoothScroller.targetPosition = position
    layoutManager?.startSmoothScroll(smoothScroller)

    if (isAnimating.not()) {
        commitCallback.invoke()
    }
}
