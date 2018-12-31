package ch.li.k.eternianproducts;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TaskGenerator {

    private static int n_tasks;
    private static final int bound2 = 2;
    private static final int bound10 = 10;
    private static final String[] ops = {"*", "\u00F7"};

    private List<Integer> arg1;
    private List<Integer> arg2;
    private List<String> operators;

    private Random rng2;
    private Random rng10;

    public TaskGenerator(int n_tasks) {

        this.n_tasks = n_tasks;
        rng2 = new Random();
        rng10 = new Random();

        arg1 = new ArrayList<>(n_tasks);
        arg2 = new ArrayList<>(n_tasks);
        operators = new ArrayList<>(n_tasks);

        for (int i=0; i<n_tasks; i++) {
            arg1.add(i, rng10.nextInt(bound10));
            arg2.add(i, rng10.nextInt(bound10));
            operators.add(i, ops[rng2.nextInt(bound2)]);
        }
        System.out.println(arg1);
        System.out.println(arg2);
        System.out.println(operators);
    }

    public List<Integer> getArg1() {
        return arg1;
    }

    public void setArg1(List<Integer> arg1) {
        this.arg1 = arg1;
    }

    public List<Integer> getArg2() {
        return arg2;
    }

    public void setArg2(List<Integer> arg2) {
        this.arg2 = arg2;
    }

    public List<String> getOperators() {
        return operators;
    }

    public void setOperators(List<String> operators) {
        this.operators = operators;
    }
}
