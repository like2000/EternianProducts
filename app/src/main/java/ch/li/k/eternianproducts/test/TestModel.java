package ch.li.k.eternianproducts.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

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
