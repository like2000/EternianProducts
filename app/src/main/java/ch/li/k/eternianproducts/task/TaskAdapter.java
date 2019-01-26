package ch.li.k.eternianproducts.task;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.Locale;
import java.util.Timer;
import java.util.function.Predicate;

import ch.li.k.eternianproducts.MainActivity;
import ch.li.k.eternianproducts.R;

import static ch.li.k.eternianproducts.R.id.video_heman;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private Uri videoUri;
    private LayoutInflater inflater;
    private TaskGenerator taskGenerator;
    private LinearLayout animationBottomBar;

    public TaskAdapter(Context context, TaskGenerator taskGenerator, CountDownTimer countDownTimer) {
        this.inflater = LayoutInflater.from(context);
        this.taskGenerator = taskGenerator;

        this.videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "heman_trafo");
        this.animationBottomBar = ((AppCompatActivity) context).findViewById(R.id.animationBottomBar);
    }

    void play_video() {
        animationBottomBar.removeAllViews();
        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.animation_heman, this.animationBottomBar);

        VideoView video = layout.findViewById(R.id.video_heman);
        video.setVideoURI(this.videoUri);
        System.out.println("Start video!!");
        video.start();

        video.setOnCompletionListener((v) -> {
            System.out.println("Finish video!");
            video.stopPlayback();
        });
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.row_task_list, parent, false);
        return new TaskViewHolder(itemView);
    }

    @Override // <-- triggered by notifyChanged()
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.arg1.setText(taskGenerator.getArg1().getValue().get(position).toString());
        holder.arg2.setText(taskGenerator.getArg2().getValue().get(position).toString());
        holder.operator.setText(taskGenerator.getOperator().getValue().get(position));

        holder.result.getText().clear();
        holder.result.setBackgroundResource(R.color.silverTrans);

        holder.result.setOnFocusChangeListener((view, hasFocus) -> {
            if (!hasFocus) {
                try {
                    taskGenerator.checkResult(Integer.parseInt(holder.result.getText().toString()), position);
                    if (taskGenerator.getResult().getValue().get(position)) {
                        holder.result.setBackgroundResource(R.color.greenTrans);
                    } else {
                        holder.result.setBackgroundResource(R.color.redTrans);
                    }
                } catch (NumberFormatException exception) {
                    holder.result.setBackgroundResource(R.color.silverTrans);
                }
            }
        });

        holder.result.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                try {
                    taskGenerator.checkResult(Integer.parseInt(holder.result.getText().toString()), position);
                    if (taskGenerator.getResult().getValue().get(position)) {
                        holder.result.setBackgroundResource(R.color.greenTrans);
                    } else {
                        holder.result.setBackgroundResource(R.color.redTrans);
                    }
                } catch (NumberFormatException exception) {
                    holder.result.setBackgroundResource(R.color.silverTrans);
                }

                try {
                    if (taskGenerator.getResult().getValue().stream().allMatch((Boolean result) -> result)) {
                        play_video();
                        System.out.println("Done!");
                    }
                } catch (NullPointerException exception) {
//                    play_video();
                    System.out.println("Not yet done!");
                }
                return true;
            }
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return taskGenerator.getN_tasks();
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView arg1, arg2;
        public TextView operator;
        public EditText result;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            arg1 = itemView.findViewById(R.id.arg1);
            arg2 = itemView.findViewById(R.id.arg2);
            result = itemView.findViewById(R.id.result);
            operator = itemView.findViewById(R.id.operator);
        }
    }
}
