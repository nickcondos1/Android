package com.example.nick.ncondoslab3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListFragment extends Fragment implements ExpandableListView.OnChildClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_LIST = "param1";


    // TODO: Rename and change types of parameters
    private ArrayList<Manufacturer> manufacturer;


    public ListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment ListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ListFragment newInstance(ArrayList<Manufacturer> manu) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_LIST, manu);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            manufacturer = (ArrayList<Manufacturer>)getArguments().getSerializable(ARG_LIST);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list, container, false);

        ExpandableListView expandList = view.findViewById(R.id.expandable);
        MyListAdapter listAdapter = new MyListAdapter(getActivity(), manufacturer);
        expandList.setAdapter(listAdapter);

        ExpandableListView v = view.findViewById(R.id.expandable);
        v.setOnChildClickListener(this);


        return view;
    }

    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id)
    {
        ((MainActivity)getActivity()).changePage(manufacturer.get(groupPosition).getModelObject(childPosition));



        /*Toast.makeText(getContext(),  manufacturer.get(groupPosition).getName() + " " +
                manufacturer.get(groupPosition).getModel(childPosition), Toast.LENGTH_SHORT).show();*/

        return true;
    }

}
