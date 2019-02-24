package com.example.nick.ncondoslab3;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {


    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        Model model = ((MainActivity)getActivity()).getSelectedModel();

        TextView name = view.findViewById(R.id.modelName);
        TextView years = view.findViewById(R.id.modelYears);
        TextView engine = view.findViewById(R.id.engineSize);
        ImageView id = view.findViewById(R.id.carPic);

        name.setText(model.getModelName());
        years.setText(model.getModelYear());
        engine.setText(model.getEngineRange());
        id.setImageResource(model.getIdModel());

        return view;
    }

}
