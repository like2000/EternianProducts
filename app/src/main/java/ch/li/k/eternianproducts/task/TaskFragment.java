package ch.li.k.eternianproducts.task;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTaskBinding;

/**
 * A fragment representing a list of Items.
 * <p/>
 * // * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskFragment extends Fragment {

    private OnListFragmentInteractionListener interactionListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public TaskFragment() {
    }

    //    // TODO: Customize parameter initialization
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
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TaskAdapter adapter = new TaskAdapter(getContext());
        RecyclerView recyclerView = getActivity().findViewById(R.id.taskList);
        TaskViewModel viewModel = ViewModelProviders.of(this).get(TaskViewModel.class);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        recyclerView.post(new Runnable() {
//            @Override
//            public void run() {
//                adapter.checkResult();
//            }
//        });

        viewModel.getTaskList().observe(this, adapter::setTaskList);
//        adapter.on
//        EditText resultField = this.getActivity().findViewById(R.id.result);
//        resultField.setOnFocusChangeListener((result, hasFocus) -> {
//            adapter.checkResult(resultField, hasFocus);
//        });

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
//        if (context instanceof OnListFragmentInteractionListener) {
//            interactionListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
    }

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        interactionListener = null;
//    }

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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction();
    }
}
