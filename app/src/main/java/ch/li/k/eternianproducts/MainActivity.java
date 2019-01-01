package ch.li.k.eternianproducts;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;

import java.util.List;

import ch.li.k.eternianproducts.databinding.ActivityMainBinding;
import ch.li.k.eternianproducts.task.TaskGenerator;

public class MainActivity extends AppCompatActivity {

    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        ActivityMainBinding binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        final RecyclerView taskList = findViewById(R.id.taskList);
        final TaskGenerator taskGenerator = new TaskGenerator(10);
        final TaskTableRowAdapter adapter = new TaskTableRowAdapter(this, taskGenerator);
        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
//        taskViewModel.getArg1().observe(this, new Observer<List<Integer>>() {
//            @Override
//            public void onChanged(@Nullable List<Integer> integers) {
//
//            }
//        });
        taskList.setLayoutManager(layoutManager);
        adapter.notifyDataSetChanged();
        taskList.setAdapter(adapter);

        ProgressBar progress = findViewById(R.id.progressBar);
//        progress.setProgressDrawable(getResources().getDrawable(R.drawable.orko));
        progress.setProgress(80);
//        final TableLayout tableLayout = findViewById(R.id.taskTable);
//        LayoutInflater inflater = LayoutInflater.from(MainActivity.this);
//        TableRow row = (TableRow) inflater.inflate(R.layout.view_task_row, tableLayout, false);
//        tableLayout.addView(row);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taskGenerator.updateTaskList();
                adapter.notifyDataSetChanged();
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

//                View image = findViewById(R.layout.view_floating_image);
//                LayoutInflater imgInflater = LayoutInflater.from(MainActivity.this);
//                ImageView img_animation = imgInflater.inflate(R.layout.view_floating_image, image, false);
//
//                TranslateAnimation animation = new TranslateAnimation(0.0f, 0.0f,
//                        800.0f, 400.0f);
//                animation.setDuration(1000);  // animation duration
//                //animation.setFillAfter(true);
//
//                img_animation.startAnimation(animation);  // start animation
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
