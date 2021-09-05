package crazyboyfeng.android.preference

import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.preference.EditTextPreferenceDialogFragmentCompat
import androidx.preference.EditTextPreferencePlus

class EditTextPreferenceDialogFragmentCompatPlus : EditTextPreferenceDialogFragmentCompat(),
    PreferenceDialogFragmentCompatInterface {
    override fun initial(key: String) {
        val bundle = Bundle(1)
        bundle.putString(ARG_KEY, key)
        arguments = bundle
    }

    override fun onBindDialogView(view: View) {
        super.onBindDialogView(view)
        val editText = view.findViewById<EditText>(android.R.id.edit)
        val editTextPreferencePlus = preference as EditTextPreferencePlus
        editText.inputType = editTextPreferencePlus.inputType
        editText.hint = editTextPreferencePlus.hint
    }
}