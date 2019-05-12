package ch.li.k.eternianproducts.test;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import ch.li.k.eternianproducts.BR;
import ch.li.k.eternianproducts.databinding.FragmentTestItemBinding;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    TestModelList testModelList;

    TestAdapter() {
        testModelList = new TestModelList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = FragmentTestItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(testModelList.get(position));
    }

    @Override
    public int getItemCount() {
        return testModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        void bind(TestModelList.TestModel model) {
            binding.setVariable(BR.var, model);
            binding.executePendingBindings();
        }
    }
}
