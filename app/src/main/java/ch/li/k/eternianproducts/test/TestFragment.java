package ch.li.k.eternianproducts.test;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.VideoView;

import java.util.Objects;

import ch.li.k.eternianproducts.MainActivity;
import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    int bound10;
    int nElements;
    private int t0 = 1000;
    private int dt = 3000;

    public FrameLayout animationBarTop;
    public FrameLayout animationBarBottom;

    private Uri videoUri;
    private VideoView video;
    private String operators;
    private TestAdapter adapter;
    private RecyclerView recyclerView;

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

        dt = 10000;
        if (operators.contentEquals("MULTIMULTI") || operators.contentEquals("MULTIDIVI")) {
            dt = 6000 * nElements;
        }

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
        View container = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_heman, animationBarBottom);
        container.setVisibility(View.VISIBLE);

        InputMethodManager inputManager = (InputMethodManager) container.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(container.getWindowToken(), 0);
        }

        ProgressBar progress = container.findViewById(R.id.progressBar);

        video = container.findViewById(R.id.video_heman); // TODO: perhaps making video global might help...!
        video.setVideoURI(this.videoUri);
        video.start();

        t0 = sharedPreferences.getInt("videoPosition", 0);
        video.setOnPreparedListener(mp -> {
            progress.setMax(video.getDuration());
            progress.setProgress(t0);
            video.seekTo(t0);
        });

        Handler videoHandler = new Handler();
        videoHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int currentPosition = video.getCurrentPosition();
                progress.setProgress((int) currentPosition);
                if (currentPosition > t0 + dt) {
                    System.out.println("Video position: " + currentPosition + " of " + t0 + dt);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("videoPosition", currentPosition);
                    video.stopPlayback();
                    editor.apply();
                    ((MainActivity) getActivity()).getMainMenu().performIdentifierAction(R.id.action_update, 0);
                } else {
                    videoHandler.postDelayed(this, 1000);
                }
            }
        }, 1000);

        video.setOnCompletionListener((v) -> {
//            timer.cancel();
            video.stopPlayback();
            ((MainActivity) getActivity()).getMainMenu().performIdentifierAction(R.id.action_update, 0);
        });
    }

    public void runAnimationOrko() {
        View animationContainer = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_orko, animationBarBottom);
        animationContainer.setVisibility(View.VISIBLE);

        TransitionManager.beginDelayedTransition(animationBarBottom);
        animationContainer.postDelayed(() -> {
            TransitionManager.beginDelayedTransition(animationBarBottom);
            animationContainer.setVisibility(View.GONE);
        }, 3000);
    }

    public void runAnimationBeastMan() {
        View animationContainer = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_game_over, animationBarBottom);
        animationContainer.setVisibility(View.GONE);

        TransitionManager.beginDelayedTransition(animationBarBottom);
        animationContainer.postDelayed(() -> {
            TransitionManager.beginDelayedTransition(animationBarBottom);
            animationContainer.setVisibility(View.VISIBLE);
        }, 3000);

        MediaPlayer player = MediaPlayer.create(getContext(), R.raw.skeletor_laugh);
        player.start();
    }

    public void runAnimationSkeletor(long tick, float timeout) {
        View animationContainer = LayoutInflater.from(getContext())
                .inflate(R.layout.animation_skeletor, animationBarTop);
        animationContainer.setAlpha((float) ((timeout - tick) / timeout));
    }
}