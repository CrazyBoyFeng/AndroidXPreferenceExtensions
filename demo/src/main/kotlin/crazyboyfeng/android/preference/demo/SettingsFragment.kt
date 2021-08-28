package crazyboyfeng.android.preference.demo

import android.os.Bundle
import androidx.preference.NumberPickerPreference
import crazyboyfeng.android.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
        val numberPickerPreference = findPreference<NumberPickerPreference>("numberpicker")
        numberPickerPreference?.minValue = 1
        numberPickerPreference?.maxValue = 15
        numberPickerPreference?.wrapSelectorWheel = true
        numberPickerPreference?.formatSummary = true
    }
}