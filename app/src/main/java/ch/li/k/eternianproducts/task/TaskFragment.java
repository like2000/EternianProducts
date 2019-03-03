package ch.li.k.eternianproducts.task;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ch.li.k.eternianproducts.R;
import ch.li.k.eternianproducts.databinding.FragmentTaskBinding;

/**
 * A fragment representing a list of Items.
 * <p/>
 * // * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TaskFragment extends Fragment {

    TaskAdapter adapter;
    TaskViewModel viewModel;
    private OnListFragmentInteractionListener interactionListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
//    public TaskFragment() {
//    }

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
        return FragmentTaskBinding.inflate(inflater, container, false).getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        adapter = new TaskAdapter(this.getContext());

        RecyclerView recyclerView = getActivity().findViewById(R.id.taskList);
        recyclerView.setAdapter(adapter);

//        System.out.println("\n\n--> Output:");
//        Log.d(this.getTag(), adapter.toString());
//        Log.d(this.getTag(), String.valueOf(viewModel.taskList.getValue().size()));
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), 2));
//        viewModel.getTaskList().observe(this, (taskModels -> adapter.setTaskList(taskModels)));
//        System.exit(-1);

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

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        if (context instanceof OnListFragmentInteractionListener) {
//            interactionListener = (OnListFragmentInteractionListener) context;
//        } else {
//            throw new RuntimeException(context.toString()
//                    + " must implement OnListFragmentInteractionListener");
//        }
//    }
//
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
