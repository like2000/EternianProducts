package ch.li.k.eternianproducts;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import ch.li.k.eternianproducts.settings.SettingsFragment;
import ch.li.k.eternianproducts.test.TestFragment;

public class MainActivity extends AppCompatActivity {

    private static final int bound10 = 12;
    private static final int nTasks = 12;
    private static final int time = 1;

    private View imageTop;
    private View imageBottom;
    private CountDownTimer countdown;
    private FrameLayout animationBarTop;
    private FrameLayout animationBarBottom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        // Toolbar
        // =======
        setSupportActionBar(findViewById(R.id.toolbar));
        getSupportActionBar().setTitle("He-Nius");

        // Inflate fragment
        // ================
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, new TestFragment())
                .addToBackStack(null)
                .commit();

        // Countdown timer
        // ===============
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
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainer, new SettingsFragment())
                    .commit();

        } else if (id == R.id.action_update) {
            TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            fragment.runAnimationOrko();
            fragment.update();

            startCountdownTimer();

        } else if (id == R.id.action_search) {
            TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            fragment.update();

            fragment.runAnimationOrko();
            startCountdownTimer();
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
