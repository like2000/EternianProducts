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
import android.widget.LinearLayout;

import ch.li.k.eternianproducts.task.TaskFragment;
import ch.li.k.eternianproducts.test.TestFragment;

public class MainActivity extends AppCompatActivity {

    private static final int bound10 = 12;
    private final int nTasks = 12;
    private CountDownTimer countdown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        final float timeout = 3 * 60 * 1000;
        countdown = new CountDownTimer((long) timeout, 3000) {
            @Override
            public void onTick(long l) {
                LinearLayout animationTopBar = findViewById(R.id.animationTopBar);
                View imageTop = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.animation_skeletor, animationTopBar);
                imageTop.setAlpha((float) ((timeout - l) / timeout));
            }

            @Override
            public void onFinish() {
                LinearLayout animationBottomBar = findViewById(R.id.animationBottomBar);
                animationBottomBar.removeAllViews();
                View imageBottom = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.animation_game_over, animationBottomBar);
                imageBottom.setVisibility(View.GONE);

                TransitionManager.beginDelayedTransition(animationBottomBar);
                imageBottom.postDelayed(() -> {
                    TransitionManager.beginDelayedTransition(animationBottomBar);
                    imageBottom.setVisibility(View.VISIBLE);
                }, 2000);

                MediaPlayer player = MediaPlayer.create(MainActivity.this, R.raw.skeletor_laugh);
                player.start();

                System.out.println("Game over!");
                this.cancel();
//                System.exit(-1);
            }
        };
        countdown.start();

        // Set toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("He-Nius");
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Inflate fragment
        getSupportFragmentManager()
                .beginTransaction()
                .setReorderingAllowed(true)
                .replace(R.id.fragmentContainer, new TestFragment())
                .addToBackStack(null)
                .commit();

//        taskList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.HORIZONTAL));
//        taskList.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
//        taskList.setLayoutManager(layoutManager);
//        taskList.setAdapter(adapter);

//        ProgressBar progress = findViewById(R.id.progressBar);
//        progress.setProgressDrawable(getResources().getDrawable(R.drawable.orko));
//        progress.setProgress(80);
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
