package ch.li.k.eternianproducts.task;

import android.arch.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskGenerator {

    private int n_tasks;

    private static final int bound2 = 2;
    private static final int bound10 = 10;
    private static final String[] ops = {"+", "+"};
//    private static final String[] ops = {"*", "\u00F7"};

    private static final Random rng2 = new Random();
    private static final Random rng10 = new Random();

    private MutableLiveData<List<Integer>> arg1;
    private MutableLiveData<List<Integer>> arg2;
    private MutableLiveData<List<String>> operator;

    public TaskGenerator(int n_tasks) {

        this.n_tasks = n_tasks;

        arg1 = new MutableLiveData<>();
        arg2 = new MutableLiveData<>();
        operator = new MutableLiveData<>();

        ArrayList<Integer> u = new ArrayList<>(n_tasks);
        ArrayList<Integer> v = new ArrayList<>(n_tasks);
        ArrayList<String> w = new ArrayList<>(n_tasks);

        for (int i=0; i<n_tasks; i++) {
            u.add(i, rng10.nextInt(bound10));
            v.add(i, rng10.nextInt(bound10));
            w.add(i, ops[rng2.nextInt(bound2)]);
        }

        setArg1(u);
        setArg2(v);
        setOperator(w);
        System.out.println(arg1.getValue());
        System.out.println(arg2.getValue());
        System.out.println(operator.getValue());
    }

    public void updateTaskList() {

        ArrayList<Integer> u = new ArrayList<>(n_tasks);
        ArrayList<Integer> v = new ArrayList<>(n_tasks);
        ArrayList<String> w = new ArrayList<>(n_tasks);

        for (int i=0; i<n_tasks; i++) {
            u.add(i, rng10.nextInt(bound10));
            v.add(i, rng10.nextInt(bound10));
            w.add(i, ops[rng2.nextInt(bound2)]);
        }

        setArg1(u);
        setArg2(v);
        setOperator(w);
    }

    public MutableLiveData<List<Integer>> getArg1() {
        if (arg1 == null) {
            arg1 = new MutableLiveData<>();
        }
        return arg1;
    }

    public void setArg1(List<Integer> arg1) {
        this.arg1.setValue(arg1);
    }

    public MutableLiveData<List<Integer>> getArg2() {
        if (arg2 == null) {
            arg2 = new MutableLiveData<>();
        }
        return arg2;
    }

    public void setArg2(List<Integer> arg2) {
        this.arg2.setValue(arg2);
    }

    public MutableLiveData<List<String>> getOperator() {
        if (operator == null) {
            operator = new MutableLiveData<>();
        }
        return operator;
    }

    public void setOperator(List<String> operator) {
        this.operator.setValue(operator);
    }

    public int getN_tasks() {
        return n_tasks;
    }
}
