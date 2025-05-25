/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.recyclerview

import android.view.MotionEvent
import androidx.recyclerview.widget.RecyclerView

/**
 * Default [RecyclerView.OnItemTouchListener] implementation that eliminates unused callback
 * boilerplate from UI.
 */
open class DefaultItemTouchListener : RecyclerView.OnItemTouchListener {

    override fun onTouchEvent(rv: RecyclerView, e: MotionEvent) {
        // no-op
    }

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        return false
    }

    override fun onRequestDisallowInterceptTouchEvent(disallowIntercept: Boolean) {
        // no-op
    }
}
