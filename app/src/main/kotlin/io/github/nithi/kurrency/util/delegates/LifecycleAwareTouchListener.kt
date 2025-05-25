/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.delegates

import android.view.MotionEvent
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.github.nithi.kurrency.util.keyboard.dismissKeyboard
import io.github.nithi.kurrency.util.keyboard.isKeyboardOpen
import io.github.nithi.kurrency.util.recyclerview.DefaultItemTouchListener

/**
 * A lifecycle aware touch listener for [RecyclerView]. Touch listener is bound to target fragment's
 * lifecycle so that it will be handled properly.
 *
 * @param fragment The Fragment to bound this listener to its lifecycle
 * @param recyclerView The RecyclerView to add touch listener
 */
class LifecycleAwareTouchListener(
    private val fragment: Fragment,
    private val recyclerView: RecyclerView
) {

    private val defaultItemTouchListener = object : DefaultItemTouchListener() {
        override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
            if (rv.isKeyboardOpen()) {
                fragment.dismissKeyboard()
            }
            return super.onInterceptTouchEvent(rv, e)
        }
    }

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                recyclerView.addOnItemTouchListener(defaultItemTouchListener)
                fragment.viewLifecycleOwnerLiveData.observe(
                    fragment, { viewLifecycleOwner ->
                        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                            override fun onDestroy(owner: LifecycleOwner) {
                                recyclerView.removeOnItemTouchListener(defaultItemTouchListener)
                            }
                        })
                    }
                )
            }
        })
    }
}
