package ch.li.k.eternianproducts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import ch.li.k.eternianproducts.task.TaskGenerator;

public class TaskViewModel extends AndroidViewModel {

    private static final int nTasks = 10;

    private LiveData<List<Integer>> arg1;
    private LiveData<List<Integer>> arg2;
    private LiveData<List<String>> operator;

    private TaskGenerator taskGenerator;

    public TaskViewModel(Application application) {
        super(application);
        taskGenerator = new TaskGenerator(10);
    }

    public LiveData<List<Integer>> getArg1() {
        return taskGenerator.getArg1();
    }

    public LiveData<List<Integer>> getArg2() {
        return taskGenerator.getArg2();
    }

    public LiveData<List<String>> getOperator() {
        return taskGenerator.getOperator();
    }

    public TaskGenerator getTaskGenerator() {
        return taskGenerator;
    }
}