package ch.li.k.eternianproducts.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;


public class TestModelList extends ArrayList<TestModelList.TestModel> {

    private static final int bound2 = 2;
    private static final Random rng2 = new Random();
    private static final Random rng10 = new Random();

    private static int bound10;
    private static String[] ops;

    private int nElements;

    TestModelList(int nElements) {

        bound10 = 12;
        this.nElements = nElements;
        switchOperators(Operators.MULTIDIVI);

        updateModelList();
    }

    void updateModelList() {
        int a, b;
        String op;

        this.clear();
        for (int i = 0; i < nElements; i++) {
            a = rng10.nextInt(bound10) + 1;
            b = rng10.nextInt(bound10) + 1;
            op = ops[rng2.nextInt(bound2)];

            this.add(new TestModel(a, b, op));
        }
    }

    private void switchOperators(Operators operations) {
        switch (operations) {
            case PLUSPLUS:
                ops = new String[]{"+", "+"};
            case PLUSMINUS:
                ops = new String[]{"+", "-"};
            case MULTIMULTI:
                ops = new String[]{"*", "*"};
            case MULTIDIVI:
                ops = new String[]{"*", "\u00F7"};
        }
    }

    private enum Operators {
        PLUSPLUS,
        PLUSMINUS,
        MULTIDIVI,
        MULTIMULTI,
    }

    public class TestModel {

        public String operator;
        public Integer arg1, arg2;
        public Integer value1, value2, value3;
        private ArrayList<Integer> valList1, valList2, valList3;

        TestModel(int n_elements) {
            Random random = new Random();

            valList1 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
            valList2 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
            valList3 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));

            value1 = random.nextInt(10);
            value2 = random.nextInt(10);
            value3 = random.nextInt(10);

            System.out.println(value1 + ", " + value2 + ", " + value3);
        }

        TestModel(int arg1, int arg2, String operator) {

            switch (operator) {
                case ("*"):
                    this.arg1 = arg1;
                    this.arg2 = arg2;
                case ("\u00F7"):
                    this.arg1 = arg1;
                    this.arg2 = arg2;
                case ("+"):
                    this.arg1 = arg1;
                    this.arg2 = arg2;
                case ("-"):
                    this.arg1 = arg1;
                    this.arg2 = arg2;
            }
            this.operator = operator;

//            for (int i = 0; i < nTasks; i++) {
//                a = rng10.nextInt(bound10) + 1;
//                b = rng10.nextInt(bound10) + 1;
//                op = ops[rng2.nextInt(bound2)];
//
//                if (op.equals("*")) {
//                    list.add(new TaskModel(a, b, op));
//                } else if (op.equals("\u00F7")) {
//                    list.add(new TaskModel(a * b, b, op));
//                } else {
//                    list.add(new TaskModel(a, b, op));
//                }
//            }
//            taskList.setValue(list);
        }
    }
}
