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
                break;
            case "PLUSMINUS":
                ops = new String[]{"+", "-"};
                break;
            case "MULTIMULTI":
                ops = new String[]{"*", "*"};
                break;
            case "MULTIDIVI":
                ops = new String[]{"*", "\u00F7"};
                break;
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
        public ColorDrawable color;
        public Integer arg1, arg2, result;

        private ArrayList<Integer> valList1;

        TestModel(int arg1, int arg2, String operator) {

            Random random = new Random();
            valList1 = random.ints(nElements).boxed().collect(Collectors.toCollection(ArrayList::new));

            switch (operator) {
                case ("*"):
                    this.arg1 = arg1;
                    this.arg2 = arg2;
                    break;
                case ("\u00F7"):
                    this.arg1 = arg1 * arg2;
                    this.arg2 = arg2;
                    break;
                case ("+"):
                    this.arg1 = arg1;
                    this.arg2 = arg2;
                    break;
                case ("-"):
                    this.arg1 = arg1 + arg2;
                    this.arg2 = arg2;
                    break;
            }

            this.operator = operator;
            this.color = silverTrans;
        }

        public String getResult() {
            return String.valueOf(this.result);
        }

        public void setResult(String result) {
            boolean out = false;
//            if ("*".equals(operator)) {
//                out = arg1 * arg2 == result;
//            } else if ("\u00F7".equals(operator)) {
//                out = arg1 / arg2 == result;
//            } else if ("+".equals(operator)) {
//                out = arg1 + arg2 == result;
//            }
//
//            if (out) {
//                setColor(greenTrans);
//                setCheck(true);
//            } else {
//                setColor(redTrans);
//                setCheck(false);
//            }
            try {
                this.result = Integer.parseInt(result);
            } catch (NumberFormatException e) {
            }
            this.color = greenTrans;
            System.out.println(this.color + ", " + this.result);
//            synchronized (TestModelList.this) {
//                TestModelList.this.notify();
//            }
        }

        public ColorDrawable getColor() {
            return color;
        }
    }
}
