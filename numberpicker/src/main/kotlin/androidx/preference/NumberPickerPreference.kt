package androidx.preference

import android.content.Context
import android.content.res.TypedArray
import android.os.Parcel
import android.os.Parcelable
import android.util.AttributeSet
import crazyboyfeng.android.preference.numberpicker.R

class NumberPickerPreference @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = R.attr.dialogPreferenceStyle,
    defStyleRes: Int = 0
) : DialogPreference(context, attrs, defStyleAttr, defStyleRes) {
    var minValue: Int
    var maxValue: Int
    var wrapSelectorWheel: Boolean
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

    override fun onGetDefaultValue(a: TypedArray, index: Int): Any {
        return a.getInt(index, if (minValue > 0) minValue else 0)
    }

    override fun onSetInitialValue(defaultValue: Any?) {
        value = getPersistedInt(defaultValue as Int)
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
            typedArray.getBoolean(R.styleable.NumberPickerPreference_wrapSelectorWheel, true)
        typedArray.recycle()
        //todo summaryprovider
    }

    private class SavedState : BaseSavedState {
        var value: Int = 0
        //TODO need save state?
//        var minValue: Int = 0
//        var maxValue: Int = 100
//        var wrapSelectorWheel = false

        constructor(source: Parcel) : super(source) {
            value = source.readInt()
//            minValue = source.readInt()
//            maxValue = source.readInt()
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                wrapSelectorWheel = source.readBoolean()
//            }
        }

        constructor(superState: Parcelable?) : super(superState)

        override fun writeToParcel(dest: Parcel, flags: Int) {
            super.writeToParcel(dest, flags)
            dest.writeInt(value)
//            dest.writeInt(minValue)
//            dest.writeInt(maxValue)
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
//                dest.writeBoolean(wrapSelectorWheel)
//            }
        }

        companion object {
            @JvmField
            val CREATOR: Parcelable.Creator<SavedState?> = object :
                Parcelable.Creator<SavedState?> {
                override fun createFromParcel(`in`: Parcel): SavedState = SavedState(`in`)
                override fun newArray(size: Int): Array<SavedState?> = arrayOfNulls(size)
            }
        }
    }
}