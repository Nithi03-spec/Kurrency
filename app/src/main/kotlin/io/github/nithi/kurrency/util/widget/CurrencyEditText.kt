/**
 * Developed by Nithillah
 * Kurrency App - Currency Converter
 */
package io.github.nithi.kurrency.util.widget

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.inputmethod.EditorInfo
import com.google.android.material.textfield.TextInputEditText
import io.github.nithi.kurrency.util.formatter.CurrencyFormatter
import io.github.nithi.kurrency.util.formatter.Formatter

/**
 * A custom [TextInputEditText] implementation which is designed for currency handling according to
 * the current locale.
 *
 * @param context The context
 * @param attributeSet The attribute set for the view
 */
class CurrencyEditText @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null
) : TextInputEditText(context, attributeSet) {

    private var isEditing = false

    private val textWatcher = object : TextWatcher {
        @Synchronized
        override fun afterTextChanged(s: Editable) {
            if (isEditing) return
            isEditing = true
            val formattedAmount = formatter.formatText(s.toString())
            setText(formattedAmount)
            setSelection(text.toString().length)
            isEditing = false
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            if (isEditing) return
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (isEditing) return
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addTextChangedListener(textWatcher)
    }

    override fun onEditorAction(actionCode: Int) {
        super.onEditorAction(actionCode)
        if (actionCode == EditorInfo.IME_ACTION_DONE) {
            clearFocus()
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        removeTextChangedListener(textWatcher)
    }

    private companion object {
        private val formatter: Formatter = CurrencyFormatter()
    }
}
