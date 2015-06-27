package com.example.next.firsapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;

import com.example.next.firsapp.activity.fragment.ShowDetailsFragment;

/**
 * Created by aluisio on 6/23/15.
 */
public class ShowFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[] { "Info", "Seasons"};

    public ShowFragmentAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    // Returns total number of pages
    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    // Returns the fragment to display for that page
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return ShowDetailsFragment.newInstance(0, "Info");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ShowDetailsFragment.newInstance(1, "Seasons");
            //case 2: // Fragment # 1 - This will show SecondFragment
               // return SecondFragment.newInstance(2, "Page # 3");
            default:
                return new Fragment();
        }
    }

    // Returns the page title for the top indicator
    @Override
    public CharSequence getPageTitle(int position) {
        return  tabTitles[position];
    }
}
