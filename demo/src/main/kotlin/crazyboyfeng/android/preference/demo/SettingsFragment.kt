package crazyboyfeng.android.preference.demo

import android.os.Bundle
import androidx.preference.EditTextPreferencePlus
import androidx.preference.NumberPickerPreference
import crazyboyfeng.android.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)

        val numberPickerPreference = findPreference<NumberPickerPreference>("numberpicker")
        numberPickerPreference?.minValue = 100
        numberPickerPreference?.maxValue = 15
        numberPickerPreference?.wrapSelectorWheel = true
        numberPickerPreference?.formatSummary = true
        numberPickerPreference?.onBindNumberPickerListener =
            NumberPickerPreference.OnBindNumberPickerListener {
                it.value = 50
            }

        val editTextPreferencePlus = findPreference<EditTextPreferencePlus>("edittext")
        editTextPreferencePlus?.formatSummary = true
    }
}