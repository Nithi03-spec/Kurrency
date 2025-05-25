/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
@file:JvmName("DelegatesKt")

package io.github.nithi.kurrency.util.ext

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import io.github.nithi.kurrency.util.delegates.ActivityViewBindingDelegate
import io.github.nithi.kurrency.util.delegates.FragmentViewBindingDelegate
import io.github.nithi.kurrency.util.delegates.ViewHolderBindingDelegate

/**
 * A delegation function that initializes [ViewBinding] for desired [Fragment]
 *
 * @param viewBindingFactory The factory to initialize binding
 */
fun <T : ViewBinding> Fragment.viewBinding(
    viewBindingFactory: (View) -> T
) = FragmentViewBindingDelegate(this, viewBindingFactory)

/**
 * A delegation function that initializes [ViewBinding] for desired [AppCompatActivity]
 *
 * @param bindingInflater The inflater to inflate view
 */
fun <T : ViewBinding> AppCompatActivity.viewBinding(
    bindingInflater: (LayoutInflater) -> T
) = ActivityViewBindingDelegate(this, bindingInflater)

/**
 * A delegation function that initializes [ViewDataBinding] for desired [RecyclerView.ViewHolder]
 */
fun <T : ViewDataBinding> RecyclerView.ViewHolder.viewHolderBinding() =
    ViewHolderBindingDelegate<T>(this)
