package crazyboyfeng.android.preference

import androidx.preference.DialogPreference
import androidx.preference.Preference

abstract class PreferenceFragmentCompat : androidx.preference.PreferenceFragmentCompat() {
    companion object {
        private const val DIALOG_FRAGMENT_TAG =
            "androidx.preference.PreferenceFragment.DIALOG"
        private val DIALOG_PREFERENCE_FRAGMENT_TYPES =
            mutableMapOf<Class<out DialogPreference>, Class<out PreferenceDialogFragmentCompatAbstract>>()

        /**
         * Sets a [DialogPreference] to use the supplied [PreferenceDialogFragmentCompatAbstract] as a dialog.
         *
         * @param dialogPreferenceClass the [DialogPreference] class to be used
         * @param preferenceDialogFragmentCompatAbstractClass the [PreferenceDialogFragmentCompatAbstract] class to be instantiated, displayed and added
         */
        fun registerPreferenceFragment(
            dialogPreferenceClass: Class<out DialogPreference>,
            preferenceDialogFragmentCompatAbstractClass: Class<out PreferenceDialogFragmentCompatAbstract>
        ) {
            DIALOG_PREFERENCE_FRAGMENT_TYPES[dialogPreferenceClass] =
                preferenceDialogFragmentCompatAbstractClass
        }
    }

    override fun onDisplayPreferenceDialog(preference: Preference) {
        if (preference !is DialogPreference) return
        if (parentFragmentManager.findFragmentByTag(DIALOG_FRAGMENT_TAG) != null) return
        if (DIALOG_PREFERENCE_FRAGMENT_TYPES.containsKey(preference.javaClass)) {
            try {
                val fragment =
                    DIALOG_PREFERENCE_FRAGMENT_TYPES[preference.javaClass]!!.newInstance()
                fragment.initialKey(preference.key)
                fragment.setTargetFragment(this, 0)
                fragment.show(parentFragmentManager, DIALOG_FRAGMENT_TAG)
            } catch (e: InstantiationException) {
                e.printStackTrace()
            } catch (e: IllegalAccessException) {
                e.printStackTrace()
            }
        } else {
            super.onDisplayPreferenceDialog(preference)
        }
    }
}