package ch.li.k.eternianproducts;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import ch.li.k.eternianproducts.databinding.ActivityMainBinding;
import ch.li.k.eternianproducts.task.TaskGenerator;
import ch.li.k.eternianproducts.task.TaskTableRowAdapter;
import ch.li.k.eternianproducts.task.TaskViewModel;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        final RecyclerView taskList = findViewById(R.id.taskList);
        final TaskGenerator taskGenerator = new TaskGenerator(14);
        final TaskTableRowAdapter adapter = new TaskTableRowAdapter(this, taskGenerator);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
//        taskViewModel.getArg1().observe(this, new Observer<List<Integer>>() {
//            @Override
//            public void onChanged(@Nullable List<Integer> integers) {
//
//            }
//        });
        taskList.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        taskList.setLayoutManager(layoutManager);
        taskList.setAdapter(adapter);

        ProgressBar progress = findViewById(R.id.progressBar);
//        progress.setProgressDrawable(getResources().getDrawable(R.drawable.orko));
        progress.setProgress(80);
//        final TableLayout tableLayout = findViewById(R.id.taskTable);
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        TableRow row = (TableRow) inflater.inflate(R.layout.view_task_row, tableLayout, false);
//        tableLayout.addView(row);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        CountDownTimer countdown = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                LinearLayout animationTopBar = findViewById(R.id.animationTopBar);
                LayoutInflater inflaterTop = LayoutInflater.from(MainActivity.this);
                View imageTop = inflaterTop.inflate(R.layout.image_skeletor, animationTopBar);
            }

            @Override
            public void onFinish() {

            }
        };
        countdown.start();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
//            boolean visible = false;

            @Override
            public void onClick(View view) {
                taskGenerator.updateTaskList();
                adapter.notifyDataSetChanged();

                LinearLayout animationBottomBar = findViewById(R.id.animationBottomBar);
                LayoutInflater inflaterBottom = LayoutInflater.from(MainActivity.this);
                View imageBottom = inflaterBottom.inflate(R.layout.image_orko, animationBottomBar);

                TransitionManager.beginDelayedTransition(animationBottomBar);
                imageBottom.setVisibility(View.VISIBLE);
                imageBottom.postDelayed(() -> {
                    TransitionManager.beginDelayedTransition(animationBottomBar);
                    imageBottom.setVisibility(View.GONE);
                }, 1500);
//                visible = !visible;
//                imageBottom.setVisibility(visible ? View.VISIBLE : View.GONE);

//            countdown.start();
//            TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
//                    0.0f, 20.0f);
//            animation.setDuration(1000);  // animation duration
//            animation.setFillAfter(true);
//            imageView.startAnimation(animation);  // start animation

//            Snackbar.make(view, imageView.toString(), Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();

            }
        });
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
