package ch.li.k.eternianproducts.task;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.VideoView;

import java.util.List;

import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTaskBinding;

/**
 * This interface must be implemented by activities that contain this
 * fragment to allow an interaction in this fragment to be communicated
 * to the activity and potentially other fragments contained in that
 * activity.
 * <p/>
 * See the Android Training lesson <a href=
 * "http://developer.android.com/training/basics/fragments/communicating.html"
 * >Communicating with Other Fragments</a> for more information.
 */
//public interface OnListFragmentInteractionListener {
//    // TODO: Update argument type and name
//    void onListFragmentInteraction();
//}

/**
 * A fragment representing a list of Items.
 * <p/>
 * // * Activities containing this fragment MUST implement the {@@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskFragment extends Fragment {

    private Uri videoUri;
    private TaskAdapter adapter;
    private LayoutInflater inflater;
    private TaskViewModel viewModel;
    private RecyclerView recyclerView;
    private LinearLayout animationBottomBar;
//    private OnListFragmentInteractionListener interactionListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskFragment() {
    }

    public void reinit() {
        viewModel.generateList();
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

// TODO: Customize parameter initialization
//    @SuppressWarnings("unused")
//    public static TaskFragment newInstance(int columnCount) {
//        TaskFragment fragment = new TaskFragment();
//        Bundle args = new Bundle();
//        args.putInt(ARG_COLUMN_COUNT, columnCount);
//        fragment.setArguments(args);
//        return fragment;
//    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTaskBinding binding = FragmentTaskBinding.inflate(inflater, container, false);
        animationBottomBar = binding.animationBottomBar;
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Adapters etc.
        adapter = new TaskAdapter(getContext());
        recyclerView = getActivity().findViewById(R.id.taskList);
        viewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(), DividerItemDecoration.HORIZONTAL));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 3));
        recyclerView.getLayoutManager().setAutoMeasureEnabled(true);

        viewModel.getTaskList().observe(this, adapter::setTaskList);
        List<TaskModel> taskList = viewModel.getTaskList().getValue();
        taskList.forEach(task -> task.getCheck().observe(this, hasChanged -> {
            boolean allCorrect = taskList.stream().allMatch(taskModel -> taskModel.getCheck().getValue());
            if (allCorrect) {
                play_video();
            }
        }));

//        viewModel.getTaskList().getValue().get(0).getCheck().observe(this, new Observer<Boolean>() {
//            @Override
//            public void onChanged(@Nullable Boolean aBoolean) {
//                this.checkResults();
//            }
//        });

//        viewModel.getCheckList().observe(this, adapter::checkResults);

//        // Set the adapter
//        if (view instanceof RecyclerView) {
//            Context context = view.getContext();
//            RecyclerView recyclerView = (RecyclerView) view;
//            if (columnCount <= 1) {
//                recyclerView.setLayoutManager(new LinearLayoutManager(context));
//            } else {
//                recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
//            }
//            recyclerView.setAdapter(new MyTaskRecyclerViewAdapter(DummyContent.ITEMS, interactionListener));
//        }

    }

//    FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener((View view) -> {
////            viewModel.generateList();
////            adapter.notifyDataSetChanged();
//
//        LinearLayout animationBottomBar = findViewById(R.id.animationBottomBar);
//        animationBottomBar.removeAllViews();
//        LayoutInflater inflaterBottom = LayoutInflater.from(MainActivity.this);
//        View imageBottom = inflaterBottom.inflate(R.layout.animation_orko, animationBottomBar);
//
//        TransitionManager.beginDelayedTransition(animationBottomBar);
//        imageBottom.setVisibility(View.VISIBLE);
//        imageBottom.postDelayed(() -> {
//            TransitionManager.beginDelayedTransition(animationBottomBar);
//            imageBottom.setVisibility(View.GONE);
//        }, 1500);
//
//        countdown.start();
////                visible = !visible;
////                imageBottom.setVisibility(visible ? View.VISIBLE : View.GONE);
//    });

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.inflater = LayoutInflater.from(context);
        this.videoUri = Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + "heman_trafo");
    }
//        if (context instanceof OnListFragmentInteractionListener) {
//            interactionListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }

    @Override
    public void onDetach() {
        super.onDetach();
//        interactionListener = null;
    }
}
