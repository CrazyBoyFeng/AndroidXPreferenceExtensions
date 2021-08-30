package androidx.preference

import android.content.Context
import android.content.res.TypedArray
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import crazyboyfeng.android.preference.NumberPickerPreferenceDialogFragmentCompat
import crazyboyfeng.android.preference.numberpicker.R
import java.util.*

class NumberPickerPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.dialogPreferenceStyle,
    defStyleRes: Int = 0
) : DialogPreference(context, attrs, defStyleAttr, defStyleRes) {
    companion object {
        init {
            crazyboyfeng.android.preference.PreferenceFragmentCompat.registerPreferenceFragment(
                NumberPickerPreference::class.java,
                NumberPickerPreferenceDialogFragmentCompat::class.java
            )
        }
    }

    private var mSummary: CharSequence?
    var minValue: Int
    var maxValue: Int
    var wrapSelectorWheel: Boolean
    var formatSummary: Boolean = false
        set(value) {
            field = value
            notifyChanged()
        }
    private var mSummary: CharSequence?
    override fun getSummary(): CharSequence? {
        val superSummary = super.getSummary()
        if (!formatSummary || mSummary == null) {
            return superSummary
        }//formatSummary
        val output = if (summaryProvider == null) value.toString() else superSummary
        if (output.isNullOrEmpty()) {
            return output
        }//formatSummaryValue
        return try {
            mSummary.toString().format(output)
        } catch (e: IllegalFormatException) {
            superSummary
        }
    }
    var value: Int = 0
        set(value) {
            val wasBlocking = shouldDisableDependents()
            field = value
            persistInt(value)
            val isBlocking = shouldDisableDependents()
            if (isBlocking != wasBlocking) {
                notifyDependencyChange(isBlocking)
            }
            notifyChanged()
        }

    private fun checkDefaultValue(defValue: Int) = when {
        defValue < minValue -> minValue
        defValue > maxValue -> maxValue
        else -> defValue
    }

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any =
        checkDefaultValue(a.getInt(index, 0))

    override fun onSetInitialValue(defaultValue: Any?) {
        val defValue= (defaultValue?:0) as Int
        value = getPersistedInt(checkDefaultValue(defValue))
    }

    override fun onSaveInstanceState(): Parcelable? {
        val superState = super.onSaveInstanceState()
        if (isPersistent) {
            // No need to save instance state since it's persistent
            return superState
        }
        val myState = SavedState(superState)
        myState.value = value
        return myState
    }

    override fun onRestoreInstanceState(state: Parcelable?) {
        if (state == null || state.javaClass != SavedState::class.java) {
            // Didn't save state for us in onSaveInstanceState
            super.onRestoreInstanceState(state)
            return
        }
        val myState = state as SavedState
        super.onRestoreInstanceState(myState.superState)
        value = myState.value
    }

    init {
        val typedArray = context.obtainStyledAttributes(
            attrs,
            R.styleable.NumberPickerPreference,
            defStyleAttr,
            defStyleRes
        )
        minValue = typedArray.getInt(R.styleable.NumberPickerPreference_minValue, 0)
        maxValue = typedArray.getInt(R.styleable.NumberPickerPreference_maxValue, 100)
        wrapSelectorWheel =
            typedArray.getBoolean(R.styleable.NumberPickerPreference_wrapSelectorWheel, false)
        formatSummary =
            typedArray.getBoolean(R.styleable.NumberPickerPreference_formatSummary, false)
        mSummary = typedArray.getString(R.styleable.Preference_summary)
        typedArray.recycle()
        if (dialogLayoutResource == 0) {
            dialogLayoutResource = R.layout.preference_dialog_numberpicker
        }
    }

    private class SavedState(superState: Parcelable?) : BaseSavedState(superState) {
        var value: Int = 0
        //TODO need save other state?

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeInt(value)
        }
    }
}