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
        value = savedInstanceState?.getInt(SAVE_STATE_VALUE) ?: getNumberPickerPreference().value
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SAVE_STATE_VALUE, value)
    }

    override fun onCreateDialogView(context: Context?): View {
//        return LinearLayout(context)//TODO
        numberPicker = NumberPicker(context)
        return numberPicker as NumberPicker
    }

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)
        numberPicker!!.requestFocus()
        numberPicker!!.minValue = getNumberPickerPreference().minValue
        numberPicker!!.maxValue = getNumberPickerPreference().maxValue
        numberPicker!!.wrapSelectorWheel = getNumberPickerPreference().wrapSelectorWheel
        numberPicker!!.value = value
    }

    private fun getNumberPickerPreference(): NumberPickerPreference {
        return preference as NumberPickerPreference
    }

    override fun onDialogClosed(positiveResult: Boolean) {
        if (positiveResult) {
            val value = numberPicker!!.value
            val preference = getNumberPickerPreference()
            if (preference.callChangeListener(value)) {
                preference.value = value
            }
        }
    }
}