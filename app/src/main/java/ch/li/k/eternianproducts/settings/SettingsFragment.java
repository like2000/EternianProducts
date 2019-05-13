package ch.li.k.eternianproducts.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceManager;

import ch.li.k.eternianproducts.R;

public class SettingsFragment extends PreferenceFragmentCompat {

    SharedPreferences.OnSharedPreferenceChangeListener listener;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);

//        Fragment fragment = getActivity().getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
//
//        listener = (sharedPreferences, key) -> {
//            String operators = sharedPreferences.getString("preference_operators", "MULTIDIVI");
//            int timeout = Integer.parseInt(sharedPreferences.getString("preference_timeout", "3"));
//            int nElements = Integer.parseInt(sharedPreferences.getString("preference_calcRange", "12"));
//
//            System.out.println("\n\n--> Updating settimgs...! " + fragment);
//        };

//        PreferenceManager.setDefaultValues(getContext(), R.xml.preferences, false);
//        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
//        preferences.registerOnSharedPreferenceChangeListener(listener);

        // TODO: Link now to fragment!

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
