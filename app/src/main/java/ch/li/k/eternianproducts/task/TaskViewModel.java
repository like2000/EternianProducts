package ch.li.k.eternianproducts.task;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import java.util.Random;

public class TaskViewModel extends AndroidViewModel {

    private static final int nTasks = 12;
    private static final int bound2 = 2;
    private static final int bound10 = 12;
    private static final String[] ops = {"+", "+"};
    private static final Random rng2 = new Random();
    //    private static final String[] ops = {"*", "\u00F7"};
    //    private static final String[] ops = {"*", "*"};
    private static final Random rng10 = new Random();
    MutableLiveData<TaskModel> taskList = new MutableLiveData<>();

    public TaskViewModel(@NonNull Application application) {
        super(application);

        generateList();
    }

    private void generateList() {
        int a, b;
        String op;

        for (int i = 0; i < nTasks; i++) {
            a = rng10.nextInt(bound10) + 1;
            b = rng10.nextInt(bound10) + 1;
            op = ops[rng2.nextInt(bound2)];

            if (op.equals("*")) {
                taskList.setValue(new TaskModel(a, b, op));
            } else if (op.equals("\u00F7")) {
                taskList.setValue(new TaskModel(a * b, b, op));
            }
        }
    }
}