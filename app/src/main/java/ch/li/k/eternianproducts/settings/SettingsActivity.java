package ch.li.k.eternianproducts.settings;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import ch.li.k.eternianproducts.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_settings);
        DataBindingUtil.setContentView(this, R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, new SettingsFragment())
                .commit();
    }

    public void leaveActivity(View v) {
        System.out.println("Leaving settings dialog!");
        finish();
    }
}
