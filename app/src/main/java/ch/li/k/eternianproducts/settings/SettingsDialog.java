package ch.li.k.eternianproducts.settings;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

import ch.li.k.eternianproducts.R;

public class SettingsDialog extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

//        findPreference("preference_timeout");
//        findPreference("preference_calcRange");
//        findPreference("preference_calculation").setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
//            @Override
//            public boolean onPreferenceChange(Preference preference, Object newValue) {
//                Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
//                fragment.upd
//
//                return true;
//            }
//        });
    }
}
