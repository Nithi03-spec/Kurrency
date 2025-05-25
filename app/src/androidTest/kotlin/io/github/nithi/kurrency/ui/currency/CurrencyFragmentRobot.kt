/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui.currency

import android.view.View
import com.google.common.truth.Truth.assertThat
import io.github.nithi.kurrency.databinding.FragmentCurrencyBinding

/**
 * Robot pattern to verify [CurrencyFragment]'s assertions in [CurrencyFragmentTest]
 *
 * Credit: https://academy.realm.io/posts/kau-jake-wharton-testing-robots/
 */
fun launchCurrencyFragment(func: CurrencyFragmentRobot.() -> Unit) =
    CurrencyFragmentRobot().apply { func() }

/**
 * A robot that includes [CurrencyFragmentTest]'s assertions.
 */
class CurrencyFragmentRobot {

    fun verifyFirstLunch(binding: FragmentCurrencyBinding) {
        assertThat(binding.pbCurrency.visibility).isEqualTo(View.VISIBLE)
        assertThat(binding.rvCurrency.visibility).isEqualTo(View.INVISIBLE)
        assertThat(binding.errorContainer.root.visibility).isEqualTo(View.GONE)
    }
}
