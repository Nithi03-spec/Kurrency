/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.delegates

import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

/**
 * A [ReadOnlyProperty] to create a [ViewDataBinding] for requested [RecyclerView.ViewHolder]
 *
 * @param viewHolder The ViewHolder that has [ViewDataBinding] enabled
 */
class ViewHolderBindingDelegate<out T : ViewDataBinding>(
    private val viewHolder: RecyclerView.ViewHolder
) : ReadOnlyProperty<RecyclerView.ViewHolder, T> {
    private var binding: T? = null

    override fun getValue(thisRef: RecyclerView.ViewHolder, property: KProperty<*>): T {
        val binding = binding
        if (binding != null) {
            return binding
        }

        val dataBinding = DataBindingUtil.getBinding<T>(viewHolder.itemView)
            ?: throw IllegalStateException("The view is not a root View for a layout or view hasn't been bound.")

        return dataBinding.also { this.binding = it }
    }
}
