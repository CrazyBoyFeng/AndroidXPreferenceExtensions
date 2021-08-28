package crazyboyfeng.android.preference

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

interface PreferenceDialogFragmentCompatInterface {
    fun initial(key: String)
    fun setTargetFragment(fragment: Fragment?, requestCode: Int)
    fun show(manager: FragmentManager, tag: String?)
}