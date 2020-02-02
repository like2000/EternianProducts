package ch.li.k.eternianproducts.test;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

import ch.li.k.eternianproducts.MainActivity;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    private static final long TMAX = 30;

    int bound10;
    int nElements;
    private String operators;
    private int t0, t1, tmax;

    public View animationContainer;
    public FrameLayout animationBarTop;
    public FrameLayout animationBarBottom;

    private Uri videoUri;
    private VideoView video;
    private TestAdapter adapter;
    private RecyclerView recyclerView;

    // Countdown timer
    // ===============
    CountDownTimer timer = new CountDownTimer(TMAX * 1000, 1000) {
        long timeOffset = 0;
        long accumulator = 0;

        @Override
        public void onTick(long tick) {
            accumulator += 2000;
            timeOffset = TMAX * 1000 - tick;
            Log.d("DEBUG", "Time offset now at: " + String.valueOf(timeOffset * 1e-3));

//            if (timeOffset * 1e-3 > 10 & timeOffset * 1e-3 < 20) {
//                video.pause();
//            } else if (timeOffset * 1e-3 > 20) {
//                Log.d("DEBUG", "Resuming video!");
////                video.seekTo(20 * 1000);
//                video.resume();
//            }
//            Log.d("\n\n--> DEBUG", "Video location: " + video.getCurrentPosition());
        }

        @Override
        public void onFinish() {
            video.stopPlayback();
            ((MainActivity) getActivity()).getMainMenu().performIdentifierAction(R.id.action_update, 0);
        }
    };

    // Fragment instantiation
    // ======================
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentTestBinding binding = FragmentTestBinding.inflate(inflater, container, false);
        animationBarBottom = binding.animationBarBottom;
        animationBarTop = binding.animationBarTop;
        recyclerView = binding.recyclerTest;

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        adapter = new TestAdapter();
        adapter.getTestModelList().getAllCorrect().observe(this, observer);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(false); // Simple fix for flickering view

        initPreferences();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "heman_castle");
    }

    Observer observer = new Observer() {
        @Override
        public void onChanged(@Nullable Object o) {
            boolean allCorrect = Objects.requireNonNull(adapter.getTestModelList().getAllCorrect().getValue())
                    .stream().allMatch(isCorrect -> isCorrect);
            if (allCorrect) {
                System.out.println("\n\n--> Change triggered! Now running video...");
                runAnimationHeMan();
            }
        }
    };

    // Preferences management
    // ======================
    SharedPreferences sharedPreferences;

    SharedPreferences.OnSharedPreferenceChangeListener listener = new SharedPreferences.OnSharedPreferenceChangeListener() {
        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            operators = sharedPreferences.getString("preference_operators", "MULTIDIVI");
            bound10 = Integer.parseInt(sharedPreferences.getString("preference_calcRange", "12"));
            nElements = Integer.parseInt(sharedPreferences.getString("preference_nElements", "12"));

            updateModel(nElements, bound10, operators);
        }
    };

    void initPreferences() {
        PreferenceManager.setDefaultValues(getContext(), R.xml.preferences, false);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener);

        operators = sharedPreferences.getString("preference_operators", "MULTIDIVI");
        bound10 = Integer.parseInt(sharedPreferences.getString("preference_calcRange", "12"));
        nElements = Integer.parseInt(sharedPreferences.getString("preference_nElements", "12"));

        updateModel(nElements, bound10, operators);
    }

    // Model interaction and animations
    // ================================
    public void updateModel() {
        adapter.testModelList.updateModelList();
        adapter.notifyDataSetChanged();
    }

    public void updateModel(int nElements, int bound10, String operators) {
        adapter.testModelList.updateModelList(nElements, bound10, operators);
        adapter.notifyDataSetChanged();

        ((MainActivity) getActivity()).startCountdownTimer();
    }

    public void runAnimationHeMan() {
//        adapter.getTestModelList().getAllCorrect().removeObservers(TestFragment.this);

        View container = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_heman, animationBarBottom);
        container.setVisibility(View.VISIBLE);

        ProgressBar progress = container.findViewById(R.id.progressBar);
        progress.setProgress(0);
        progress.setMax(100);

        video = container.findViewById(R.id.video_heman); // TODO: perhaps making video global might help...!
        video.setVideoURI(this.videoUri);
        video.start();
//        timer.start();

        AtomicInteger duration = new AtomicInteger();
        video.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                duration.set(video.getDuration());
            }
        });

//        int current;
//        int duration = video.getDuration();
//
//        do {
//            current = video.getCurrentPosition();
//            System.out.println("duration - " + duration + " current- " + current);
//            try {
//                progress.setProgress(((int) (current * 100 / duration)));
//                if(progress.getProgress() >= 100){
//                    break;
//                }
//            } catch (Exception e) {
//            }
//        } while (progress.getProgress() <= 100);

        Handler videoHandler = new Handler();
        videoHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = video.getCurrentPosition();
                System.out.println("Video position: " + currentPosition);
                if (video.getCurrentPosition() > 10000) {
//                Log.d("DEBUG", "Video time " + video.getCurrentPosition());
//                timer.cancel();
                    video.stopPlayback();
                    ((MainActivity) getActivity()).getMainMenu().performIdentifierAction(R.id.action_update, 0);
                }
                else {
                    videoHandler.postDelayed(this, 1000);
                }
            }
        }, 1000
        );

//        video.setOnCompletionListener((v) -> {
//            timer.cancel();
//            video.stopPlayback();
//            ((MainActivity) getActivity()).getMainMenu().performIdentifierAction(R.id.action_update, 0);
//        });
    }

    public void runAnimationOrko() {
        // Why remove observer!?!
//        adapter.getTestModelList().getAllCorrect().removeObservers(TestFragment.this);

//        try {
//            animationBarBottom.removeAllViews();
//        } catch (NullPointerException ignored) {
//        }

        View container = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_orko, animationBarBottom);
        container.setVisibility(View.VISIBLE);

        TransitionManager.beginDelayedTransition(animationBarBottom);
        container.postDelayed(() -> {
            TransitionManager.beginDelayedTransition(animationBarBottom);
            container.setVisibility(View.GONE);
        }, 3000);

        // Restart observing all correct
//        new Handler().postDelayed(() -> adapter.getTestModelList().getAllCorrect().observe(TestFragment.this, observer), 1000);
    }

    public void runAnimationBeastMan() {
//        try {
//            animationBarBottom.removeAllViews();
//        } catch (NullPointerException ignored) {
//        }

        View container = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_game_over, animationBarBottom);
        container.setVisibility(View.GONE);

        TransitionManager.beginDelayedTransition(animationBarBottom);
        container.postDelayed(() -> {
            TransitionManager.beginDelayedTransition(animationBarBottom);
            container.setVisibility(View.VISIBLE);
        }, 3000);

        MediaPlayer player = MediaPlayer.create(getContext(), R.raw.skeletor_laugh);
        player.start();
    }

    public void runAnimationSkeletor(long tick, float timeout) {
        animationContainer = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_skeletor, animationBarTop);
        animationContainer.setAlpha((float) ((timeout - tick) / timeout));
    }
}