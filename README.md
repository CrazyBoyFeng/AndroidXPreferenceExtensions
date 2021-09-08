# AndroidX Preference Extensions
Android library: Extensions of AndroidX Preference.

## Usage
0. Add dependency through [JitPack](https://jitpack.io/#CrazyBoyFeng/AndroidXPreferenceExtensions).
1. XML
```xml
<PreferenceScreen>
    ...
    <EditTextPreferencePlus
        android:hint="hint"
        android:inputType="number"
        android:maxLength="4"
        app:formatSummary="true"
        app:key="edittext"
        app:summary="text is %s!"
        app:title="edittext" />
    ...
    <NumberPickerPreference
        app:formatSummary="true"
        app:key="numberpicker"
        app:maxValue="20"
        app:minValue="10"
        app:summary="%s sheep"
        app:title="numberpicker"
        app:wrapSelectorWheel="true" />
    ...
</PreferenceScreen>
```
2. Fragment
```java
import crazyboyfeng.android.preference.PreferenceFragmentCompat;
class MyPreferenceFragment extends PreferenceFragmentCompat {}
```

