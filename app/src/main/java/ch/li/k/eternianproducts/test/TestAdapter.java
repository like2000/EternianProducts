package ch.li.k.eternianproducts.test;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import ch.li.k.eternianproducts.R;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    final int N_ELEMENTS = 100;
    Random random = new Random();
    ArrayList<Integer> valuesArray1;
    ArrayList<Integer> valuesArray2;
    ArrayList<Integer> valuesArray3;

    public TestAdapter() {
        valuesArray1 = random.ints(N_ELEMENTS).boxed().collect(Collectors.toCollection(ArrayList::new));
        valuesArray2 = random.ints(N_ELEMENTS).boxed().collect(Collectors.toCollection(ArrayList::new));
        valuesArray3 = random.ints(N_ELEMENTS).boxed().collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_test_item, null);
        ViewHolder holder = new ViewHolder(item);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return N_ELEMENTS;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
