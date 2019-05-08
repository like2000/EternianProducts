package ch.li.k.eternianproducts.test;

import java.util.ArrayList;
import java.util.Random;
import java.util.stream.Collectors;

public class TestModel {

    private Random random = new Random();
    public ArrayList<Integer> valList1, valList2, valList3;

    public TestModel(int n_elements) {
        valList1 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
        valList2 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
        valList3 = random.ints(n_elements).boxed().collect(Collectors.toCollection(ArrayList::new));
    }
}
