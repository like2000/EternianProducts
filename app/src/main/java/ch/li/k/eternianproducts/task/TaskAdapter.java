package ch.li.k.eternianproducts.task;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.eternianproducts.BR;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTaskItemBinding;

import static android.content.ContentValues.TAG;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Uri videoUri;
    private boolean isVirign;
    private LayoutInflater inflater;
    private RecyclerView recyclerView;
    private LinearLayout animationBottomBar;
    private List<TaskModel> taskList = new ArrayList<>();

    TaskAdapter(Context context) {
        this.isVirign = true;
        this.inflater = LayoutInflater.from(context);
        this.animationBottomBar = ((AppCompatActivity) context).findViewById(R.id.animationBottomBar);
        this.videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "heman_trafo");
    }

    public void setTaskList(List<TaskModel> taskList) {
        this.taskList.clear();
        this.taskList.addAll(taskList);
        notifyDataSetChanged();
    }

    void play_video() {
        animationBottomBar.removeAllViews();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.animation_heman, this.animationBottomBar);
        layout.setVisibility(View.VISIBLE);

        VideoView video = layout.findViewById(R.id.video_heman);
        video.setVideoURI(this.videoUri);
        System.out.println("Start video!!");
        video.start();

        video.setOnCompletionListener((v) -> {
            System.out.println("Finish video!");
            video.stopPlayback();
            video.seekTo(0);
        });
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
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

        holder.resultField.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER || keyCode == KeyEvent.KEYCODE_TAB)) {
                    try {
                        String result = holder.resultField.getText().toString();
                        taskList.get(holder.getAdapterPosition()).checkResult(Integer.parseInt(result));

                        notifyItemChanged(position);
                    } catch (NumberFormatException exception) {
                        Log.d(TAG, exception.getMessage());
                    }

                    try {
                        EditText editText = recyclerView.getChildAt(position + 1).findViewById(R.id.result);
                        editText.requestFocus();
                    } catch (NullPointerException exception) {
                        EditText editText = recyclerView.getChildAt(0).findViewById(R.id.result);
                        editText.requestFocus();
                    }

//                try {
//                    if (taskGenerator.getResult().getValue().stream().allMatch((Boolean result) -> result)) {
//                        play_video();
//                        System.out.println("Done!");
//                    }
//                } catch (NullPointerException exception) {
////                    play_video();
//                    System.out.println("Not yet done!");
//                }

                    isVirign = false;
                    return true;
                }
                return false;
            }
        });

        TaskModel task = taskList.get(position);
        holder.bind(task);

        if (isVirign) {
            holder.resultField.getText().clear();
        }

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


//                    recyclerView.post(new Runnable()
//                    {
//                        @Override
//                        public void run() {
//                            notifyDataSetChanged();
//                        }
//                    });
//                    System.out.println(task.getResult() + " " + holder.resultField.getText().toString());
//                    System.out.println(task.getColor().getColor() + " " + ((ColorDrawable) holder.resultField.getBackground()).getColor());
//                }
//        );

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

        TaskViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
            this.resultField = binding.getRoot().findViewById(R.id.result);
        }

        public void bind(TaskModel task) {
//            binding.setTask(task);
            binding.setVariable(BR.task, task);
            binding.executePendingBindings();
        }
    }
}
