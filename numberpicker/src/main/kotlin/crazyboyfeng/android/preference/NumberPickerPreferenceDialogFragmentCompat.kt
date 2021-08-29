package crazyboyfeng.android.preference

import android.os.Bundle
import android.view.View
import android.widget.NumberPicker
import androidx.preference.NumberPickerPreference
import crazyboyfeng.android.preference.numberpicker.R

class NumberPickerPreferenceDialogFragmentCompat : PreferenceDialogFragmentCompatAbstract() {
    companion object {
        private const val SAVE_STATE_VALUE = "NumberPickerDialogPreferenceFragment.value"
    }

    private var numberPicker: NumberPicker? = null
    private var value: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val numberPickerPreference = preference as NumberPickerPreference
        value = savedInstanceState?.getInt(SAVE_STATE_VALUE) ?: numberPickerPreference.value
    }

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)
        numberPicker = view.findViewById(R.id.number)
        numberPicker!!.requestFocus()
        val numberPickerPreference = preference as NumberPickerPreference
        numberPicker!!.minValue = numberPickerPreference.minValue
        numberPicker!!.maxValue = numberPickerPreference.maxValue
        numberPicker!!.wrapSelectorWheel = numberPickerPreference.wrapSelectorWheel
        if (value > numberPicker!!.maxValue) {
            value = numberPicker!!.maxValue
        } else if (value < numberPicker!!.minValue) {
            value = numberPicker!!.minValue
        }
        numberPicker!!.value = value
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            val value = numberPicker!!.value
            val numberPickerPreference = preference as NumberPickerPreference
            if (numberPickerPreference.callChangeListener(value)) {
                numberPickerPreference.value = value
            }
        }
    }
}