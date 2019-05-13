package ch.li.k.eternianproducts;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import ch.li.k.eternianproducts.settings.SettingsActivity;
import ch.li.k.eternianproducts.settings.SettingsFragment;
import ch.li.k.eternianproducts.test.TestFragment;

public class MainActivity extends AppCompatActivity {

    private static final int bound10 = 12;
    private static final int nTasks = 12;
    private static final int time = 1;

    private CountDownTimer countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        // Toolbar
        setSupportActionBar(findViewById(R.id.toolbar));

        // Preferences
//        PreferenceManager.setDefaultValues(this, R.xml.preferences, false);
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        String marketPref = sharedPref
//                .getString("sync_frequency", "-1");

        // Inflate fragment
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, new TestFragment())
                .addToBackStack(null)
                .commit();

        // Countdown timer
        startCountdownTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            countdown.cancel();

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new SettingsFragment())
                    .commit();

            return true;
        } else if (id == R.id.action_update) {
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

    private void startCountdownTimer() {
        float timeout = time * 60 * 1000;
        try {
            countdown.cancel();
        } catch (NullPointerException e) {
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
}
