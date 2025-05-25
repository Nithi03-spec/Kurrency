/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.ui.currency

import android.content.Context
import android.view.LayoutInflater
import androidx.test.annotation.UiThreadTest
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.github.nithi.kurrency.databinding.FragmentCurrencyBinding
import org.junit.Test
import org.junit.runner.RunWith

/**
 * A test class for [CurrencyFragment]
 */
@RunWith(AndroidJUnit4::class)
class CurrencyFragmentTest {

    @Test
    @UiThreadTest
    fun testFragmentLaunchesAndViewsAreInCorrectVisibility() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val layoutInflater = LayoutInflater.from(context)
        val binding = FragmentCurrencyBinding.inflate(layoutInflater)

        launchCurrencyFragment {
            verifyFirstLunch(binding)
        }
    }
}
