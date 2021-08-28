package crazyboyfeng.android.preference

import android.content.Context
import android.os.Bundle

import android.view.View
import android.widget.NumberPicker
import androidx.preference.NumberPickerPreference

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

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_VALUE, value)
    }

    override fun onCreateDialogView(context: Context?): View {
        numberPicker = NumberPicker(context)
        return numberPicker as NumberPicker
    }

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)
        val numberPickerPreference = preference as NumberPickerPreference
        numberPicker!!.requestFocus()
        numberPicker!!.minValue = numberPickerPreference.minValue
        numberPicker!!.maxValue = numberPickerPreference.maxValue
        numberPicker!!.wrapSelectorWheel = numberPickerPreference.wrapSelectorWheel
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