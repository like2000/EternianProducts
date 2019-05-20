package ch.li.k.eternianproducts.test;

import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.EditText;

import ch.li.k.eternianproducts.BR;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTestItemBinding;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    TestModelList testModelList;
    private RecyclerView recyclerView;

    TestAdapter() {
        testModelList = new TestModelList();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewDataBinding binding = FragmentTestItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        // Bit more logic now...
        // =====================
        holder.result.setOnFocusChangeListener((v, hasFocus) -> {
            if (!recyclerView.isComputingLayout()) {
                notifyItemChanged(position);
            }
        });

//        System.out.println("--> Recycler View: " + recyclerView + ", computing layout: " + recyclerView.isComputingLayout());
//        if (recyclerView != null && !recyclerView.isComputingLayout()) {
//        Handler handler = new Handler();
//        holder.result.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                if (hasFocus) {
//                    System.out.println("--> Dataset changed!");
//                    handler.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            notifyItemChanged(position);
//                        }
//                    });
////                notifyItemChanged(position);
////                    notifyDataSetChanged();
//                }
//            }
//        });
//        }

        holder.bind(testModelList.get(position));
        holder.result.getText().clear();
    }

    @Override
    public int getItemCount() {
        return testModelList.size();
    }

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
