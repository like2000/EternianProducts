package ch.li.k.eternianproducts;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import ch.li.k.eternianproducts.settings.SettingsActivity;
import ch.li.k.eternianproducts.test.TestFragment;

public class MainActivity extends AppCompatActivity {

    private int timeout;
    private Menu mainMenu;
    private CountDownTimer countdown;

    // Main activity instantiation
    // ===========================
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        // Set main content
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        // Preferences
        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

        // Inflate fragment
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, new TestFragment())
                .addToBackStack(null)
                .commit();

        // Countdown timer
        timeout = Integer.parseInt(sharedPreferences.getString("preference_timeout", "3"));
        startCountdownTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        this.mainMenu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            countdown.cancel();
//
//            getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.fragmentContainer, new SettingsFragment())
//                    .commit();
//
//            return true;
        if (id == R.id.action_update) {
            countdown.cancel();

            TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            fragment.runAnimationOrko();
            fragment.updateModel();

            startCountdownTimer();

            return true;
        } else if (id == R.id.action_preferences) {
            countdown.cancel();

            startActivity(new Intent(MainActivity.this, SettingsActivity.class));

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Preferences management
    // ======================
    SharedPreferences sharedPreferences;
    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            timeout = Integer.parseInt(sharedPreferences.getString("preference_timeout", "3"));
        }
    };

    // Main activity countdown timer
    // =============================
    public void startCountdownTimer() {
        float timeout = this.timeout * 60 * 1000;
        try {
            countdown.cancel();
        } catch (NullPointerException ignored) {
        }

        countdown = new CountDownTimer((long) timeout, 3000) {

            @Override
            public void onTick(long tick) {
                TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                fragment.runAnimationSkeletor(tick, timeout);
            }

            @Override
            public void onFinish() {
                TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
                fragment.runAnimationBeastMan();

                this.cancel();
            }
        };
        countdown.start();
    }

    public Menu getMainMenu() {
        return mainMenu;
    }
}
