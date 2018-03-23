package com.example.shivam.practice;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by shivam on 18/1/18.
 */

public class HeadlineFragment extends android.support.v4.app.Fragment {

    TabLayout tabs;
    View view;
    ViewPager viewPager;
    String title[] = {"India","Sports","World","Business","Politics","Technology","Entertainment"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.headline_fragment,container,false);

        // setting adapter with the viewpager ...
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new SlidingAdapter(getChildFragmentManager()));

        //setting viewpager with the tabs ...
        tabs = (TabLayout) view.findViewById(R.id.sliding_tabs);
        tabs.post(new Runnable(){
            @Override
            public void run() {
                tabs.setupWithViewPager(viewPager);
            }
        });

        // setting toolbar title to headlines...
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Headlines");

        return view;
    }

    /**
     * This class is for handling tabs according to the fragment ...
     */
    private class SlidingAdapter extends FragmentPagerAdapter{


        public SlidingAdapter(FragmentManager fm) {
            super(fm);
        }


        /**
         *
         * @param position
         * @return fragment according to the position ...
         */
        @Override
        public Fragment getItem(int position) {
            if(position==0)
               return new IndiaFragment();
            else if(position==1)
               return new SportsFragment();
            else if(position==2)
                return new WorldFragment();
            else if(position==3)
                return new BusinessFragment();
            else if(position==4)
                return new PoliticsFragment();
            else if(position==5)
                return new TechnologyFragment();
            else
                return new EntertainmentFragment();
        }



        /**
         * @return total number of tabs in the tab layout ...
         */
        @Override
        public int getCount() { return 7; }



        /**
         * @param position is the position of the tab for which title is to return ...
         * @return title of the tab in the tab layout ...
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return title[position];
        }
    }

}
