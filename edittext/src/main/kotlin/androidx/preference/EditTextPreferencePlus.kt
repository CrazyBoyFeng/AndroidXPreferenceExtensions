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

    private var mSummary: CharSequence?
    var formatSummary: Boolean
    override fun getSummary(): CharSequence? {
        val superSummary = super.getSummary()
        if (!formatSummary || mSummary == null) {
            return superSummary
        }//formatSummary
        val output = if (summaryProvider == null) text else superSummary
        if (output.isNullOrEmpty()) {
            return output
        }//formatSummaryValue
        return try {
            mSummary.toString().format(output)
        } catch (e: IllegalFormatException) {
            superSummary
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
        mSummary = typedArray.getString(R.styleable.Preference_summary)
        typedArray.recycle()
    }
}