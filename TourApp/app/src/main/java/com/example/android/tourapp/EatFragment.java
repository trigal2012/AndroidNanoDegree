package com.example.android.tourapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EatFragment extends Fragment {


    public EatFragment() {
        // Required empty public constructor
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        //change R.layout.yourlayoutfilename for each of your fragments
        View rootView = inflater.inflate(R.layout.fragment_1_container, container, false);

        // Create a list of items
        final ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(R.string.category_eat, R.string.date9, R.string.type,R.string.name10,R.string.hours3, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date8, R.string.type,R.string.name11,R.string.hours2, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date7, R.string.type,R.string.name12,R.string.hours2, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date6, R.string.type,R.string.name13,R.string.hours2, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date5, R.string.type,R.string.name14,R.string.hours2, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date4, R.string.type,R.string.name15,R.string.hours3, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date3, R.string.type,R.string.name16,R.string.hours3, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date2, R.string.type,R.string.name17,R.string.hours2, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date1, R.string.type,R.string.name18,R.string.hours3, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        items.add(new Item(R.string.category_eat, R.string.date0, R.string.type,R.string.name19,R.string.hours0, R.string.address,
                R.string.phone,R.string.details, R.string.artist,R.string.lat, R.string.lng));

        //call the Item adapter to get a row for each item in the list created above
        ItemAdapter adapter = new ItemAdapter(getActivity(), items, "eat");

        //find the area of the fragment_*_container to add the row
        ListView listView = (ListView) rootView.findViewById(R.id.list);

        // add the adapter to the view, so we get a row for each item in the array, the row is returned from the ItemAdapter
        listView.setAdapter(adapter);

        //replace fragment_*_container.xml with fragment_details_container
        //use a fragment_details_item in the fragment_details_container
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Get the Item object at the given position the user clicked on
                Item item = items.get(position);

                //replacing the fragment
                //creating fragment object
                Fragment fragment = new DetailsFragment();

                //add the extras
                Bundle bundle = new Bundle();
                bundle.putInt("nameId", item.getmNameId());
                bundle.putInt("hoursId", item.getmHoursId());
                bundle.putInt("phoneId", item.getmPhoneId());
                bundle.putInt("addressId", item.getmAddressId());
                bundle.putInt("detailsId", item.getmDetailsId());

                fragment.setArguments(bundle);

                //replace the fragment
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.content_frame, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        return rootView;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setting the fragment title in the toolbar
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);
        toolbar.setTitle("Brew, Grinds, and Grub");
    }

}
