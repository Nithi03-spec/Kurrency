/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui.currency

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.nithi.kurrency.data.model.view.RateViewItem
import io.github.nithi.kurrency.databinding.LayoutCurrencyItemBinding
import io.github.nithi.kurrency.databinding.LayoutCurrencyResponderItemBinding
import io.github.nithi.kurrency.ui.di.MainScope
import io.github.nithi.kurrency.util.event.SingleLiveEvent
import io.github.nithi.kurrency.util.recyclerview.BaseViewHolder
import javax.inject.Inject

@MainScope
class CurrencyAdapter @Inject constructor(
    private val itemClickLiveData: SingleLiveEvent<String>,
    private val bindableMultiplier: BindableMultiplier
) : ListAdapter<RateViewItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            ITEM_RESPONDER -> {
                val binding = LayoutCurrencyResponderItemBinding.inflate(inflater, parent, false)
                ResponderViewHolder(binding.root)
            }
            ITEM_CURRENCY -> {
                val binding = LayoutCurrencyItemBinding.inflate(inflater, parent, false)
                CurrencyViewHolder(binding.root)
            }
            else -> throw IllegalStateException("No view type found for $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ResponderViewHolder -> {
                val rate = currentList.first()
                holder.bindTo(rate)
            }
            is CurrencyViewHolder -> {
                val rate = currentList[position]
                holder.bindTo(rate)
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (position) {
                0 -> {
                    holder as ResponderViewHolder
                    val rate = currentList.first()
                    holder.bindTo(rate)
                }
                else -> {
                    holder as CurrencyViewHolder
                    val rate = currentList[position]
                    holder.bindTo(rate)
                }
            }
        }
    }

    /**
     * Moves tapped item to the top position.
     *
     * @param position The layout position
     * @param commitCallback The function to be called
     */
    private fun moveToTop(position: Int, commitCallback: (() -> Unit)) {
        currentList.toMutableList().removeAt(position).also { rate ->
            currentList.toMutableList().add(1, rate)
        }
        notifyItemMoved(position, 1)
        commitCallback.invoke()
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 0) ITEM_RESPONDER else ITEM_CURRENCY
    }

    inner class ResponderViewHolder(itemView: View) :
        BaseViewHolder<LayoutCurrencyResponderItemBinding, RateViewItem>(itemView) {

        override fun bindTo(item: RateViewItem) {
            binding.rate = item
            binding.bindable = bindableMultiplier

            super.bindTo(item)
        }
    }

    inner class CurrencyViewHolder(itemView: View) :
        BaseViewHolder<LayoutCurrencyItemBinding, RateViewItem>(itemView) {

        override fun bindTo(item: RateViewItem) {
            binding.rate = item
            binding.bindable = bindableMultiplier

            binding.root.setOnClickListener {
                moveToTop(layoutPosition) {
                    bindableMultiplier.reset()
                    itemClickLiveData.value = item.abbreviation
                }
            }

            super.bindTo(item)
        }
    }

    private companion object {
        private const val ITEM_RESPONDER = 0
        private const val ITEM_CURRENCY = 1

        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<RateViewItem>() {
            override fun areItemsTheSame(oldItem: RateViewItem, newItem: RateViewItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: RateViewItem, newItem: RateViewItem): Boolean {
                return oldItem == newItem
            }

            override fun getChangePayload(oldItem: RateViewItem, newItem: RateViewItem): Any? {
                return if (oldItem.id != newItem.id) newItem.id else oldItem.id
            }
        }
    }
}
