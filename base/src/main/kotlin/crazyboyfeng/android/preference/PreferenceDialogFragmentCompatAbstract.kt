package crazyboyfeng.android.preference

import android.os.Bundle
import androidx.preference.PreferenceDialogFragmentCompat

abstract class PreferenceDialogFragmentCompatAbstract : PreferenceDialogFragmentCompat(),
    PreferenceDialogFragmentCompatInterface {
    override fun initial(key: String) {
        val bundle = Bundle(1)
        bundle.putString(ARG_KEY, key)
        arguments = bundle
    }
}