package crazyboyfeng.android.preference

import android.os.Bundle
import androidx.preference.EditTextPreferenceDialogFragmentCompat

class EditTextPreferenceDialogFragmentCompatPlus: EditTextPreferenceDialogFragmentCompat(),PreferenceDialogFragmentCompatInterface {
    override fun initial(key: String) {
        val bundle = Bundle(1)
        bundle.putString(ARG_KEY, key)
        arguments = bundle
    }
}