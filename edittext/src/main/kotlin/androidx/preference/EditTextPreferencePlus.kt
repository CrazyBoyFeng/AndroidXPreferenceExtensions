package androidx.preference

import android.content.Context
import android.util.AttributeSet
import crazyboyfeng.android.preference.EditTextPreferenceDialogFragmentCompatPlus
import crazyboyfeng.android.preference.edittext.R
import java.util.*

class EditTextPreferencePlus @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.editTextPreferenceStyle,
    defStyleRes: Int = 0
) : EditTextPreference(context, attrs, defStyleAttr, defStyleRes) {
    companion object {
        init {
            crazyboyfeng.android.preference.PreferenceFragmentCompat.registerPreferenceFragment(
                EditTextPreferencePlus::class.java,
                EditTextPreferenceDialogFragmentCompatPlus::class.java
            )
        }
    }
    var formatSummary: Boolean
    override fun getSummary(): CharSequence {
        val summary = super.getSummary()
        if (!formatSummary) return summary
        if (summary.isNullOrEmpty()) return summary
        return try {
            summary.toString().format(text)
        } catch (e: IllegalFormatException) {
            summary
        }
    }

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.EditTextPreferencePlus,
            defStyleAttr,
            defStyleRes
        )
        formatSummary =
            typedArray.getBoolean(R.styleable.EditTextPreferencePlus_formatSummary, false)
        typedArray.recycle()
    }
}