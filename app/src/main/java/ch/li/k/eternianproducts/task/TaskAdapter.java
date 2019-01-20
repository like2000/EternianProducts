package ch.li.k.eternianproducts.task;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Locale;

import ch.li.k.eternianproducts.MainActivity;
import ch.li.k.eternianproducts.R;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private LayoutInflater inflater;
    private TaskGenerator taskGenerator;

    public TaskAdapter(Context context, TaskGenerator taskGenerator) {
        this.inflater = LayoutInflater.from(context);
        this.taskGenerator = taskGenerator;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_task_list, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override // <-- triggered by notifyChanged()
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        int firstPosition = position;
        holder.arg11.setText(taskGenerator.getArg1().getValue().get(firstPosition).toString());
        holder.arg12.setText(taskGenerator.getArg2().getValue().get(firstPosition).toString());
        holder.operator1.setText(taskGenerator.getOperator().getValue().get(firstPosition));

        System.out.println("\n\n-->");
        System.out.println(position);
        holder.result1.getText().clear();
        holder.result1.setBackgroundResource(R.color.silverTrans);
        holder.result1.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                try {
                    taskGenerator.checkResult(Integer.parseInt(holder.result1.getText().toString()), firstPosition);
                    if (taskGenerator.getResult().getValue().get(firstPosition)) {
                        holder.result1.setBackgroundResource(R.color.greenTrans);
                    } else {
                        holder.result1.setBackgroundResource(R.color.redTrans);
                    }
                } catch (NumberFormatException exception) {
                    holder.result1.setBackgroundResource(R.color.silverTrans);
                }
            }
        });
        holder.result1.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    this.regsiterResult(holder, position);
                    return true;
                }
                return false;
        });

        int secondPosition = position + getItemCount();
        holder.arg21.setText(String.format(Locale.GERMAN, "%d", taskGenerator.getArg1().getValue().get(secondPosition)));
        holder.arg22.setText(String.format(Locale.GERMAN, "%d", taskGenerator.getArg2().getValue().get(secondPosition)));
        holder.operator2.setText(taskGenerator.getOperator().getValue().get(secondPosition));

        holder.result2.getText().clear();
        holder.result2.setBackgroundResource(R.color.silverTrans);
        holder.result2.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                try {
                    taskGenerator.checkResult(Integer.parseInt(holder.result2.getText().toString()), secondPosition);
                    if (taskGenerator.getResult().getValue().get(secondPosition)) {
                        holder.result2.setBackgroundResource(R.color.greenTrans);
                    } else {
                        holder.result2.setBackgroundResource(R.color.redTrans);
                    }
                } catch (NumberFormatException exception) {
                    holder.result2.setBackgroundResource(R.color.silverTrans);
                }
            }
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show();
        });
        holder.result2.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    holder.result2.clearFocus();
//                    MainActivity.this.findViewById(R.layout.activity_main).requestFocus();
                    return true;
                }
                return false;
            }
        });
    }

    public void regsiterResult(TaskViewHolder holder, int firstPosition) {
        try {
            taskGenerator.checkResult(Integer.parseInt(holder.result1.getText().toString()), firstPosition);
            if (taskGenerator.getResult().getValue().get(firstPosition)) {
                holder.result1.setBackgroundResource(R.color.greenTrans);
            } else {
                holder.result1.setBackgroundResource(R.color.redTrans);
            }
        } catch (NumberFormatException exception) {
            holder.result1.setBackgroundResource(R.color.silverTrans);
        }
    }

    @Override
    public int getItemCount() {
        return taskGenerator.getN_tasks() / 2;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView arg11, arg12, arg21, arg22;
        public TextView operator1, operator2;
        public EditText result1, result2;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            arg11 = itemView.findViewById(R.id.arg11);
            arg12 = itemView.findViewById(R.id.arg12);
            arg21 = itemView.findViewById(R.id.arg21);
            arg22 = itemView.findViewById(R.id.arg22);
            result1 = itemView.findViewById(R.id.result1);
            result2 = itemView.findViewById(R.id.result2);
            operator1 = itemView.findViewById(R.id.operator1);
            operator2 = itemView.findViewById(R.id.operator2);
        }
    }
}
