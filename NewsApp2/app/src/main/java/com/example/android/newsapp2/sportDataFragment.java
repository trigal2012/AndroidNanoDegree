package com.example.android.newsapp2;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class sportDataFragment extends Fragment {
    View view;
    ViewPager viewPager;
    TabLayout tabLayout;
    String saEdition;
    String bundleEdition;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(getArguments() != null && getArguments().containsKey("Edition")){
            bundleEdition = getArguments().getString("Edition");
        }
        view = inflater.inflate(R.layout.sample, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new sliderAdapter(getChildFragmentManager(), bundleEdition));
        tabLayout = view.findViewById(R.id.sliding_tabs);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }

    private class sliderAdapter extends FragmentPagerAdapter{

        public sliderAdapter(FragmentManager fm, String bundleEdition) {
            super(fm);
            saEdition = bundleEdition;
        }

        @Override
        public Fragment getItem(int position) {
            return new contentFragment();
        }

        @Override
        public int getCount() {
            return 7;
        }

        final String tabs[] = setTabs();

        public String[] setTabs(){
            String tempTabs[] = new String[0];
            if(saEdition.equals("us")) {
                tempTabs = new String[]{"Soccer", "NFL", "Tennis", "MLB", "MLS", "NBA", "NHL"};
            }else if(saEdition.equals("uk")){
                tempTabs = new String[]{"Football", "Rugby Union", "Cricket", "Tennis", "Cycling", "F1", "Golf", "Boxing", "Rugby League", "Racing"};
            }else if(saEdition.equals("au")){
                tempTabs = new String[]{"Football", "AFL", "NRL", "A-League", "Cricket", "Rugby Union", "Tennis"};
            }
            return tempTabs;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }
    }
}