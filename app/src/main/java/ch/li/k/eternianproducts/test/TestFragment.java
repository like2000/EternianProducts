package ch.li.k.eternianproducts.test;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.VideoView;

import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTestBinding;

public class TestFragment extends Fragment {

    public ImageView imageTop;
    public ImageView imageBottom;
    public FrameLayout animationBarTop;
    public FrameLayout animationBarBottom;

    Uri videoUri;
    TestAdapter adapter;
    RecyclerView recyclerView;

    public TestFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        FragmentTestBinding binding = FragmentTestBinding.inflate(inflater, container, false);

//        AnimationSkeletorBinding bindingSkeletor = AnimationSkeletorBinding.inflate(
//                inflater, getActivity().findViewById(R.id.animationBarTop), false);
//        bindingSkeletor.executePendingBindings();

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = getActivity().findViewById(R.id.recyclerTest);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        adapter = new TestAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "heman_trafo");
    }

    public void update() {
        adapter.testModelList.updateModelList();
        adapter.notifyDataSetChanged();
    }

    void runAnimationHeMan() {
        FrameLayout animationBottomBar = getActivity().findViewById(R.id.animationBarBottom);
        try {
            animationBottomBar.removeAllViews();
        } catch (NullPointerException e) {
        }
        View container = LayoutInflater.from(getContext()).inflate(R.layout.animation_heman, animationBottomBar);
        container.setVisibility(View.VISIBLE);

        VideoView video = container.findViewById(R.id.video_heman);
        video.setVideoURI(this.videoUri);
        video.start();

        video.setOnCompletionListener((v) -> {
            video.stopPlayback();
            video.seekTo(0);
        });
    }
}