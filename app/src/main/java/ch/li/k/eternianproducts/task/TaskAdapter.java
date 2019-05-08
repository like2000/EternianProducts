package ch.li.k.eternianproducts.task;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.eternianproducts.BR;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTaskItemBinding;

import static android.content.ContentValues.TAG;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private boolean isVirign;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private List<TaskModel> taskList = new ArrayList<>();

    TaskAdapter(Context context) {
        this.isVirign = true;
        this.inflater = LayoutInflater.from(context);
    }

    public void setTaskList(List<TaskModel> taskList) {
        this.taskList.clear();
        this.taskList.addAll(taskList);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(FragmentTaskItemBinding.inflate(inflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {

//        holder.resultField.setOnKeyListener((view, keyCode, keyEvent) -> {
//            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
//                    (keyCode == KeyEvent.KEYCODE_ENTER ||
//                            keyCode == KeyEvent.KEYCODE_TAB ||
//                            keyCode == KeyEvent.KEYCODE_DPAD_DOWN ||
//                            keyCode == KeyEvent.KEYCODE_DPAD_UP)) {
//                try {
//                    String result = holder.resultField.getText().toString();
//                    taskList.get(holder.getAdapterPosition()).checkResult(Integer.parseInt(result));
//
//                    notifyItemChanged(holder.getAdapterPosition());
//                } catch (NumberFormatException exception) {
//                    Log.d(TAG, exception.getMessage());
//                }
//
//                try {
//                    if (keyCode == KeyEvent.KEYCODE_TAB || keyCode == KeyEvent.KEYCODE_DPAD_DOWN) {
//                        EditText editText = recyclerView.getChildAt(holder.getAdapterPosition() + 1).findViewById(R.id.result);
//                        editText.requestFocus();
//                    } else if (keyCode == KeyEvent.KEYCODE_DPAD_UP) {
//                        EditText editText = recyclerView.getChildAt(holder.getAdapterPosition() - 1).findViewById(R.id.result);
//                        editText.requestFocus();
//                    }
//                } catch (NullPointerException exception) {
//                    EditText editText = recyclerView.getChildAt(0).findViewById(R.id.result);
//                    editText.requestFocus();
//                }
//                isVirign = false;
//                return true;
//            }
//            return false;
//        });

        if (isVirign) {
            holder.resultField.getText().clear();
        }

        holder.bind(taskList.get(position));


//        holder.resultField.setOnFocusChangeListener(
//                (view, hasFocus) -> {
//                    try {
//                        int result = Integer.parseInt(holder.resultField.getText().toString());
//                        taskList.get(position).checkResult(result);
//                    } catch (NumberFormatException exception) {
//                    }
//                    if (recyclerView != null && !recyclerView.isComputingLayout()) {
//                        notifyDataSetChanged();
//                    }

//        holder.result.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
//            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
//                try {
//                    taskGenerator.checkResult(Integer.parseInt(holder.result.getText().toString()), position);
//                    if (taskGenerator.getResult().getValue().get(position)) {
//                        holder.result.setBackgroundResource(R.color.greenTrans);
//                    } else {
//                        holder.result.setBackgroundResource(R.color.redTrans);
//                    }
//                } catch (NumberFormatException exception) {
//                    holder.result.setBackgroundResource(R.color.silverTrans);
//                }

//            return false;
//        });
    }

    @Override
    public int getItemCount() {
        return taskList == null ? 0 : taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;
        private final EditText resultField;

        TaskViewHolder(@NonNull ViewDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.resultField = binding.getRoot().findViewById(R.id.result);
        }

        void bind(TaskModel task) {
            binding.setVariable(BR.task, task);
            binding.executePendingBindings();
        }
    }
}
