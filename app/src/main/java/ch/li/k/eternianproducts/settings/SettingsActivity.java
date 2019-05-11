package ch.li.k.eternianproducts.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import ch.li.k.eternianproducts.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new SettingsDialog())
                .commit();
    }

    void leaveActivity() {
        finish();
    }
}
