package com.example.android.tourapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailsFragment extends Fragment {

    public DetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_details_container, container, false);
        //find the area of the fragment_*_container to add the row
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle = this.getArguments();

        LinearLayout myLayout = (LinearLayout) getActivity().findViewById(R.id.list);

        if (bundle != null) {
            //get all of the keys and values sent in the bundle
            for (String key : bundle.keySet()) {

                //determine which views to use, based on the keys in the bundle
                //add values to those views
                //update the sibling textview text to match the data

                if (key == "nameId") {
                    TextView text_1_label = (TextView) myLayout.findViewById(R.id.text_1_label);
                    text_1_label.setText(R.string.details_name);
                    TextView text_1_value = (TextView) myLayout.findViewById(R.id.text_1_value);
                    text_1_value.setText((Integer) bundle.get(key));
                }
                if (key == "artistId") {
                    TextView text_2_label = (TextView) myLayout.findViewById(R.id.text_2_label);
                    text_2_label.setText(R.string.details_artist);
                    TextView text_2_value = (TextView) myLayout.findViewById(R.id.text_2_value);
                    text_2_value.setText((Integer) bundle.get(key));
                }
                if (key == "phoneId") {
                    TextView text_2_label = (TextView) myLayout.findViewById(R.id.text_2_label);
                    text_2_label.setText(R.string.details_phone);
                    TextView text_2_value = (TextView) myLayout.findViewById(R.id.text_2_value);
                    text_2_value.setText((Integer) bundle.get(key));
                }
                if (key == "dateId") {
                    TextView text_2_label = (TextView) myLayout.findViewById(R.id.text_2_label);
                    text_2_label.setText(R.string.details_date);
                    TextView text_2_value = (TextView) myLayout.findViewById(R.id.text_2_value);
                    text_2_value.setText((Integer) bundle.get(key));
                }
                if (key == "latId") {
                    TextView text_2_label = (TextView) myLayout.findViewById(R.id.text_2_label);
                    text_2_label.setText(R.string.year_built);
                    TextView text_2_value = (TextView) myLayout.findViewById(R.id.text_2_value);
                    text_2_value.setText((Integer) bundle.get(key));
                }
                if (key == "lngId") {
                    TextView text_3_label = (TextView) myLayout.findViewById(R.id.text_3_label);
                    text_3_label.setText(R.string.building_type);
                    TextView text_3_value = (TextView) myLayout.findViewById(R.id.text_3_value);
                    text_3_value.setText((Integer) bundle.get(key));
                }
                if (key == "typeId") {
                    TextView text_3_label = (TextView) myLayout.findViewById(R.id.text_3_label);
                    text_3_label.setText(R.string.details_type);
                    TextView text_3_value = (TextView) myLayout.findViewById(R.id.text_3_value);
                    text_3_value.setText((Integer) bundle.get(key));
                }
                if (key == "hoursId") {
                    TextView text_3_label = (TextView) myLayout.findViewById(R.id.text_3_label);
                    text_3_label.setText(R.string.details_hours);
                    TextView text_3_value = (TextView) myLayout.findViewById(R.id.text_3_value);
                    text_3_value.setText((Integer) bundle.get(key));
                }
                if (key == "addressId") {
                    TextView text_4_label = (TextView) myLayout.findViewById(R.id.text_4_label);
                    text_4_label.setText(R.string.details_address);
                    TextView text_4_value = (TextView) myLayout.findViewById(R.id.text_4_value);
                    text_4_value.setText((Integer) bundle.get(key));
                }
                if (key == "detailsId") {
                    TextView text_5_label = (TextView) myLayout.findViewById(R.id.text_5_label);
                    text_5_label.setText(R.string.additional_details);
                    TextView text_5_value = (TextView) myLayout.findViewById(R.id.text_5_value);
                    text_5_value.setText((Integer) bundle.get(key));
                }
            }
        }
    }
}
