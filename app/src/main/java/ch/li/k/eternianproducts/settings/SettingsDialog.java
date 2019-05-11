package ch.li.k.eternianproducts.settings;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import ch.li.k.eternianproducts.R;

public class SettingsDialog extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferences, rootKey);
    }
}
