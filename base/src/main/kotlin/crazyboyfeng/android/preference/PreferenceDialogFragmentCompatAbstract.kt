package crazyboyfeng.android.preference

import android.os.Bundle
import androidx.preference.PreferenceDialogFragmentCompat

abstract class PreferenceDialogFragmentCompatAbstract: PreferenceDialogFragmentCompat() {
    private lateinit var key: String
    fun initialKey(key:String) {
        this.key = key
        val bundle = Bundle(1)
        bundle.putString(ARG_KEY, key)
        arguments = bundle
    }
}