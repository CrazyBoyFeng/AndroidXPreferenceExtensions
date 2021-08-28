# AndroidX Preference Extensions
Android library: Extensions of AndroidX Preference.

## Usage
0. Add dependency through [JitPack](https://jitpack.io/#CrazyBoyFeng/AndroidXPreferenceExtensions).
1. XML
```xml
<PreferenceScreen>
    ...
    <NumberPickerPreference
        app:key="numberpicker"
        app:maxValue="20"
        app:minValue="10"
        app:summary="%dsheep"
        app:title="title"
        app:wrapSelectorWheel="false" />
    ...
</PreferenceScreen>
```
2. Fragment
```kotlin
import crazyboyfeng.android.preference.PreferenceFragmentCompat
class MyPreferenceFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.settings_preferences, rootKey)
        // val numberPickerPreference = findPreference<NumberPickerPreference>("numberpicker")
        // numberPickerPreference?.minValue = 1
        // numberPickerPreference?.maxValue = 15
        // numberPickerPreference?.wrapSelectorWheel = true
    }
}
```