package com.example.next.firsapp.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.next.firsapp.activity.fragment.ShowDetailsFragment;
import com.example.next.firsapp.activity.fragment.ShowSeasonFragment;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.remote.service.OnClickSeasonListener;

/**
 * Created by aluisio on 6/23/15.
 */
public class ShowFragmentAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 2;
    private String tabTitles[] = new String[] { "Info", "Seasons"};
    private Show show;
    private OnClickSeasonListener onClickSeasonListener;

    public ShowFragmentAdapter(FragmentManager fragmentManager, Show show, OnClickSeasonListener onClickSeasonListener) {
        super(fragmentManager);
        this.show = show;
        this.onClickSeasonListener = onClickSeasonListener;
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
                return ShowDetailsFragment.newInstance(0, "Info", show);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return ShowSeasonFragment.newInstance(1, "Seasons", show, this.onClickSeasonListener);
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
