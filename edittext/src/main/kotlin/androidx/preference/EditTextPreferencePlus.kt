package androidx.preference

import android.content.Context
import android.text.InputType
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
    var maxLength: Int
    var inputType: Int
    var hint: CharSequence?

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
        mSummary = typedArray.getString(R.styleable.Preference_summary)
        formatSummary =
            typedArray.getBoolean(R.styleable.EditTextPreferencePlus_formatSummary, false)
        maxLength =
            typedArray.getInt(R.styleable.EditTextPreferencePlus_android_maxLength, Int.MAX_VALUE)
        inputType = typedArray.getInt(
            R.styleable.EditTextPreferencePlus_android_inputType,
            InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_FLAG_MULTI_LINE
        )
        hint = typedArray.getString(R.styleable.EditTextPreferencePlus_android_hint)
        typedArray.recycle()
    }
}