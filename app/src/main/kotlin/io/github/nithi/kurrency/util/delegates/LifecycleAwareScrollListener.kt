/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.delegates

import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import io.github.nithi.kurrency.util.ext.i

/**
 * A lifecycle aware scroll listener for [RecyclerView]. Scroll listener is bound to target
 * fragment's lifecycle so that it will be handled properly.
 *
 * @param fragment The Fragment to bound this listener to its lifecycle
 * @param recyclerView The RecyclerView to add scroll listener
 * @param onStateChanged The callback that holds RecyclerView scroll state
 */
class LifecycleAwareScrollListener(
    private val fragment: Fragment,
    private val recyclerView: RecyclerView,
    private val onStateChanged: (newState: Int) -> Unit
) {

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            onStateChanged.invoke(newState)
            i { "Current state is $newState" }
        }
    }

    init {
        fragment.lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                recyclerView.addOnScrollListener(scrollListener)
                fragment.viewLifecycleOwnerLiveData.observe(
                    fragment, { viewLifecycleOwner ->
                        viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                            override fun onDestroy(owner: LifecycleOwner) {
                                recyclerView.removeOnScrollListener(scrollListener)
                            }
                        })
                    }
                )
            }
        })
    }
}
