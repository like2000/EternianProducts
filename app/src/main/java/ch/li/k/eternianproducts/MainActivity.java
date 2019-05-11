package ch.li.k.eternianproducts;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import ch.li.k.eternianproducts.task.TaskFragment;
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
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("He-Nius");
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Top and bottom animationbars
        // ============================
//        animationBarTop = findViewById(R.id.animationBarTop);
//        animationBarBottom = findViewById(R.id.animationBarBottom);
//        imageTop = LayoutInflater.from(MainActivity.this)
//                .inflate(R.layout.animation_skeletor, animationBarTop);
//        imageBottom = LayoutInflater.from(MainActivity.this)
//                .inflate(R.layout.animation_game_over, animationBarBottom);

        // Inflate fragment
        // ================
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, new TestFragment())
                .addToBackStack(null)
                .commit();

        // Countdonw timer
        // ===============
        startCountdownTimer();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
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
            TaskFragment fragment = (TaskFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            fragment.reinit();

            LinearLayout animationBottomBar = findViewById(R.id.animationBottomBar);
            animationBottomBar.removeAllViews();
            View imageBottom = LayoutInflater.from(MainActivity.this)
                    .inflate(R.layout.animation_orko, animationBottomBar);

            TransitionManager.beginDelayedTransition(animationBottomBar);
            imageBottom.setVisibility(View.VISIBLE);
            imageBottom.postDelayed(() -> {
                TransitionManager.beginDelayedTransition(animationBottomBar);
                imageBottom.setVisibility(View.GONE);
            }, 2000);

            countdown.start();
//                visible = !visible;
//                imageBottom.setVisibility(visible ? View.VISIBLE : View.GONE);
        } else if (id == R.id.action_search) {
            TestFragment fragment = (TestFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainer);
            fragment.update();

            runAnimationOrko();
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
                FrameLayout animationBarTop = findViewById(R.id.animationBarTop);
                View imageTop = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.animation_skeletor, animationBarTop);
                imageTop.setAlpha((float) ((timeout - tick) / timeout));
                System.out.println(tick + ", " + imageTop.getAlpha());
            }

            @Override
            public void onFinish() {
                runAnimationBeastMan();
                this.cancel();
            }
        };
        countdown.start();
    }

//    void runAnimationSkeletor(long tick) {
//        // Strangely enough this must be in here...! Cannot go outside
//        FrameLayout animationBarTop = findViewById(R.id.animationBarTop);
//        View imageTop = LayoutInflater.from(MainActivity.this)
//                .inflate(R.layout.animation_skeletor, animationBarTop);
////        imageTop.setAlpha((float) ((timeout - tick) / timeout));
//        System.out.println(tick + ", " + imageTop.getAlpha());
//    }

    void runAnimationBeastMan() {
        FrameLayout animationBarBottom = findViewById(R.id.animationBarBottom);
        animationBarBottom.removeAllViews();
        View imageBottom = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.animation_game_over, animationBarBottom);
        imageBottom.setVisibility(View.GONE);

        TransitionManager.beginDelayedTransition(animationBarBottom);
        imageBottom.postDelayed(() -> {
            TransitionManager.beginDelayedTransition(animationBarBottom);
            imageBottom.setVisibility(View.VISIBLE);
        }, 2000);

        MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.skeletor_laugh);
        player.start();
    }

    void runAnimationOrko() {
        FrameLayout animationBottomBar = findViewById(R.id.animationBarBottom);
        try {
            animationBottomBar.removeAllViews();
        } catch (NullPointerException e) {
        }
        View imageBottom = LayoutInflater.from(MainActivity.this)
                .inflate(R.layout.animation_orko, animationBottomBar);

        TransitionManager.beginDelayedTransition(animationBottomBar);
        imageBottom.setVisibility(View.VISIBLE);
        imageBottom.postDelayed(() -> {
            TransitionManager.beginDelayedTransition(animationBottomBar);
            imageBottom.setVisibility(View.GONE);
        }, 3000);
    }
}
