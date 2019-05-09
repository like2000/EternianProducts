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

    TestModelList(int nElements) {
        bound10 = 12;
        ops = new String[]{"*", "\u00F7"};
//        ops = {"+", "+"};
//        ops = {"*", "*"};

        for (int i = 0; i < nElements; i++)
            this.add(new TestModel(1));
    }

    void updateModelList() {
        int nElements = this.size();
        this.clear();
        for (int i = 0; i < nElements; i++)
            this.add(new TestModel(1));
    }

    public class TestModel {

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
    }
}
