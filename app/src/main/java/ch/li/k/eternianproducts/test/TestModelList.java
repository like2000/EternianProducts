package ch.li.k.eternianproducts.test;

import android.graphics.drawable.ColorDrawable;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;


public class TestModelList extends ArrayList<TestModelList.TestModel> {
    private static final ColorDrawable redTrans = new ColorDrawable(0xa0ff0000);
    private static final ColorDrawable greenTrans = new ColorDrawable(0xa0228B22);
    private static final ColorDrawable silverTrans = new ColorDrawable(0xA0C0C0C0);

    private static final Random rng10 = new Random();
    private static final Random rng2 = new Random();
    private static final int bound2 = 2;

    private String operations = "MULTIDIVI";
    private int nElements = 12;
    private int bound10 = 12;
    private String[] ops;

    TestModelList() {
        updateModelList();
    }

    void updateModelList() {
        int a, b;
        String op;

        switchOperators(this.operations);

        this.clear();
        for (int i = 0; i < nElements; i++) {
            a = rng10.nextInt(bound10) + 1;
            b = rng10.nextInt(bound10) + 1;
            op = ops[rng2.nextInt(bound2)];

            this.add(new TestModel(a, b, op));
        }
    }

    void updateModelList(int nElements, int bound10, String operations) {
        this.operations = operations;
        this.nElements = nElements;
        this.bound10 = bound10;

        this.updateModelList();
    }

    private void switchOperators(String operations) {
        switch (operations) {
            case "PLUSPLUS":
                ops = new String[]{"+", "+"};
            case "PLUSMINUS":
                ops = new String[]{"+", "-"};
            case "MULTIMULTI":
                ops = new String[]{"*", "*"};
            case "MULTIDIVI":
                ops = new String[]{"*", "\u00F7"};
        }
    }

//    public void setOperations(String operations) {
//        this.operations = operations;
//        this.updateModelList();
//    }
//
//    public void setnElements(int nElements) {
//        this.nElements = nElements;
//        this.updateModelList();
//    }
//
//    public void setBound10(int bound10) {
//        this.bound10 = bound10;
//        this.updateModelList();
//    }

    public class TestModel {
        public String operator;
        public Integer arg1, arg2, result;
        public Integer value1, value2, value3;

        private ColorDrawable color;
        private ArrayList<Integer> valList1, valList2, valList3;

        TestModel(int n_elements) {
            Random random = new Random();

            valList1 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
            valList2 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
            valList3 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));

            value1 = random.nextInt(10);
            value2 = random.nextInt(10);
            value3 = random.nextInt(10);
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
//                if (op.equals("*")) {
//                    list.add(new TaskModel(a, b, op));
//                } else if (op.equals("\u00F7")) {
//                    list.add(new TaskModel(a * b, b, op));
//                } else {
//                    list.add(new TaskModel(a, b, op));
//                }
            this.operator = operator;
        }

        public String getResult() {
            return String.valueOf(this.result);
        }

        public void setResult(String result) {
            this.result = Integer.parseInt(result);
        }
    }
}
