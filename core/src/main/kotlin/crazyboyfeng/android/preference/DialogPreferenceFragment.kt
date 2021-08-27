package crazyboyfeng.android.preference

import androidx.fragment.app.DialogFragment

abstract class DialogPreferenceFragment : DialogFragment() {
    abstract var key: String
}