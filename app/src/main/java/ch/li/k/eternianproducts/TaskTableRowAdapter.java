package ch.li.k.eternianproducts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Locale;

import ch.li.k.eternianproducts.task.TaskGenerator;

public class TaskTableRowAdapter extends RecyclerView.Adapter<TaskTableRowAdapter.TaskViewHolder> {

    private LayoutInflater inflater;
    private TaskGenerator taskGenerator;

    public TaskTableRowAdapter(Context context, TaskGenerator taskGenerator) {
        this.inflater = LayoutInflater.from(context);
        this.taskGenerator = taskGenerator;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.from(parent.getContext()).inflate(R.layout.view_task_row, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.arg11.setText(String.format(Locale.GERMAN, "%d", taskGenerator.getArg1().getValue().get(position)));
        holder.arg12.setText(String.format(Locale.GERMAN, "%d", taskGenerator.getArg2().getValue().get(position)));
        holder.arg21.setText(String.format(Locale.GERMAN, "%d", taskGenerator.getArg1().getValue().get(position)));
        holder.arg22.setText(String.format(Locale.GERMAN, "%d", taskGenerator.getArg2().getValue().get(position)));
        holder.operator1.setText(taskGenerator.getOperator().getValue().get(position));
        holder.operator2.setText(taskGenerator.getOperator().getValue().get(position));
    }


    @Override
    public int getItemCount() {
        return taskGenerator.getN_tasks();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView arg11, arg12, arg21, arg22;
        public TextView operator1, operator2, res1, res2;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            arg11 = itemView.findViewById(R.id.arg11);
            arg12 = itemView.findViewById(R.id.arg12);
            arg21 = itemView.findViewById(R.id.arg21);
            arg22 = itemView.findViewById(R.id.arg22);
            operator1 = itemView.findViewById(R.id.operator1);
            operator2 = itemView.findViewById(R.id.operator2);
        }
    }
}
