package ch.li.k.eternianproducts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

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
}