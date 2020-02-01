package ch.li.k.eternianproducts.test;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;

import ch.li.k.eternianproducts.BR;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTestItemBinding;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private boolean isVirgin;
    TestModelList testModelList;

    // Adapter for one row - model to view
    // ===================================
    TestAdapter() {
        isVirgin = true;
//        setHasStableIds(true);
        testModelList = new TestModelList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = FragmentTestItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Bit more logic now... - need more listeners!
        // ============================================
        holder.result.setOnFocusChangeListener((v, hasFocus) -> {
            holder.result.postDelayed(() -> {
                if (!hasFocus) {
                    System.out.println("--> Output: " + holder.result.getText());
                    if (holder.result.getText() != null) {
                        checkCorrect();
                        isVirgin = false;
                        notifyItemChanged(position);
                    }
                }
            }, 100);
        });

        holder.bind(testModelList.get(position));
        if (isVirgin) holder.result.getText().clear();
    }

    @Override
    public int getItemCount() {
        return testModelList.size();
    }

    // Method to check if all values are good - is observed by fragment
    private void checkCorrect() {
        ArrayList<Boolean> allCorrect = new ArrayList<>();

        for (TestModelList.TestModel testModel : testModelList) {
            allCorrect.add(testModel.isCorrect());
        }

        testModelList.getAllCorrect().setValue(allCorrect);
    }

    TestModelList getTestModelList() {
        return testModelList;
    }

    // Key element - view holder with data binding
    // ===========================================
    class ViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        private final EditText result;

        ViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.result = binding.getRoot().findViewById(R.id.edResult);
        }

        void bind(TestModelList.TestModel model) {
            binding.setVariable(BR.var, model);
            binding.executePendingBindings();
        }
    }
}
