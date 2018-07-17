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

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArtFragment extends Fragment {


    public ArtFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        View rootView = inflater.inflate(R.layout.fragment_1_container, container, false);

        // Create a list of items
        final ArrayList<Item> items = new ArrayList<Item>();
        items.add(new Item(R.string.category_art, R.string.date5, R.string.type5, R.string.name20, R.string.hours0, R.string.address9,
                R.string.phone, R.string.details, R.string.artist1, R.string.lat, R.string.lng, R.drawable.one));

        items.add(new Item(R.string.category_art, R.string.date6, R.string.type5, R.string.name21, R.string.hours1, R.string.address0,
                R.string.phone, R.string.details, R.string.artist2, R.string.lat, R.string.lng, R.drawable.ten));

        items.add(new Item(R.string.category_art, R.string.date7, R.string.type5, R.string.name22, R.string.hours2, R.string.address1,
                R.string.phone, R.string.details, R.string.artist3, R.string.lat, R.string.lng, R.drawable.five));

        items.add(new Item(R.string.category_art, R.string.date8, R.string.type5, R.string.name23, R.string.hours3, R.string.address2,
                R.string.phone, R.string.details, R.string.artist4, R.string.lat, R.string.lng, R.drawable.two));

        items.add(new Item(R.string.category_art, R.string.date9, R.string.type5, R.string.name24, R.string.hours4, R.string.address3,
                R.string.phone, R.string.details, R.string.artist5, R.string.lat, R.string.lng, R.drawable.six));

        items.add(new Item(R.string.category_art, R.string.date0, R.string.type5, R.string.name25, R.string.hours0, R.string.address4,
                R.string.phone, R.string.details, R.string.artist6, R.string.lat, R.string.lng, R.drawable.eight));

        items.add(new Item(R.string.category_art, R.string.date1, R.string.type5, R.string.name26, R.string.hours1, R.string.address5,
                R.string.phone, R.string.details, R.string.artist7, R.string.lat, R.string.lng, R.drawable.seven));

        items.add(new Item(R.string.category_art, R.string.date2, R.string.type5, R.string.name27, R.string.hours2, R.string.address6,
                R.string.phone, R.string.details, R.string.artist8, R.string.lat, R.string.lng, R.drawable.nine));

        items.add(new Item(R.string.category_art, R.string.date3, R.string.type5, R.string.name28, R.string.hours3, R.string.address7,
                R.string.phone, R.string.details, R.string.artist9, R.string.lat, R.string.lng, R.drawable.four));

        items.add(new Item(R.string.category_art, R.string.date4, R.string.type5, R.string.name29, R.string.hours4, R.string.address8,
                R.string.phone, R.string.details, R.string.artist0, R.string.lat, R.string.lng, R.drawable.three));

        //call the Item adapter to get a row for each item in the list created above
        ItemAdapter adapter = new ItemAdapter(getActivity(), items, "art");

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
                bundle.putInt("artistId", item.getmArtistId());
                bundle.putInt("typeId", item.getmTypeId());
                bundle.putInt("addressId", item.getmAddressId());
                bundle.putInt("detailsId", item.getmDetailsId());
                bundle.putInt("imageResourceId", item.getmImageResourceId());

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
        toolbar.setTitle(R.string.art_outside);
    }

}
