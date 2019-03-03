package ch.li.k.eternianproducts.task;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import ch.li.k.eternianproducts.BR;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTaskBinding;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Uri videoUri;
    private LayoutInflater inflater;
    private List<TaskModel> taskList;
    private LinearLayout animationBottomBar;

    TaskAdapter(Context context) {
        this.taskList = new ArrayList<>();
        this.inflater = LayoutInflater.from(context);
        this.videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "heman_trafo");
        this.animationBottomBar = ((AppCompatActivity) context).findViewById(R.id.animationBottomBar);
    }

    public List<TaskModel> getTaskList() {
        return taskList;
    }

    public void setTaskList(List<TaskModel> taskList) {
        this.taskList = taskList;
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

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TaskViewHolder(FragmentTaskBinding.inflate(inflater, parent, false));
    }

    @Override // <-- triggered by notifyChanged()
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(taskList.get(position));

//        holder.arg1.setText(taskGenerator.getArg1().getValue().get(position).toString());
//        holder.arg2.setText(taskGenerator.getArg2().getValue().get(position).toString());
//        holder.operator.setText(taskGenerator.getOperator().getValue().get(position));

//        holder.result.getText().clear();
//        holder.result.setBackgroundResource(R.color.silverTrans);

//        holder.result.setOnFocusChangeListener((view, hasFocus) -> {
//            if (!hasFocus) {
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
//            }
//        });

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
//
//                try {
//                    if (taskGenerator.getResult().getValue().stream().allMatch((Boolean result) -> result)) {
//                        play_video();
//                        System.out.println("Done!");
//                    }
//                } catch (NullPointerException exception) {
////                    play_video();
//                    System.out.println("Not yet done!");
//                }
//                return true;
//            }
//            return false;
//        });
    }

    @Override
    public int getItemCount() {
        return this.taskList.size();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding binding;

        TaskViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());

            this.binding = binding;
        }

        public void bind(TaskModel task) {
//            binding.setTask(task);
            binding.setVariable(BR.task, task);
            binding.executePendingBindings();
        }
    }
}
