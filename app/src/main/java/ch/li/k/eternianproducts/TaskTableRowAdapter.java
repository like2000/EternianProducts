package ch.li.k.eternianproducts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class TaskTableRowAdapter extends RecyclerView.Adapter<TaskTableRowAdapter.TaskViewHolder> {

    private LayoutInflater inflater;

    public TaskTableRowAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder taskViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
