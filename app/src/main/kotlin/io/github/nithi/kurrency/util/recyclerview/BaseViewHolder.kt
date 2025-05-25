/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.recyclerview

import android.view.View
import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.github.nithi.kurrency.util.ext.viewHolderBinding

/**
 * An open base class for [RecyclerView.ViewHolder] to initialize binding and execute accordingly.
 *
 * @param itemView The root view
 */
open class BaseViewHolder<DB : ViewDataBinding, T : Any>(itemView: View) :
    RecyclerView.ViewHolder(itemView) {

    internal val binding: DB by viewHolderBinding()

    /**
     * Binds given item to this binding
     *
     * @param item represents the data
     */
    @CallSuper
    open fun bindTo(item: T) {
        if (binding.hasPendingBindings()) {
            binding.executePendingBindings()
        }
    }
}
