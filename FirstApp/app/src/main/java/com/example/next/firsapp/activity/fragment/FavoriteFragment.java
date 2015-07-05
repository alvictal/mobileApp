package com.example.next.firsapp.activity.fragment;


import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.activity.ShowDetailsActivity;
import com.example.next.firsapp.adapter.FavoriteAdapter;
import com.example.next.firsapp.core.FavoriteLoaderCallback;
import com.example.next.firsapp.core.FavoriteLoaderCursorContent;
import com.example.next.firsapp.remote.service.FavoriteCursor;
import com.example.next.firsapp.remote.service.OnClickFavoriteListener;

/**
 * Created by movile on 05/07/15.
 */
public class FavoriteFragment extends Fragment implements FavoriteCursor, OnClickFavoriteListener {
    // Store instance variables based on arguments passed
    private Cursor mCursor;
    private FavoriteAdapter mAdapter;
    private ListView mLv;
    private View mHeader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.favorite_shows_list, container, false);
        mHeader = view.inflate(getActivity(), R.layout.favorite_header_layout, null);

        mLv = (ListView) view.findViewById(R.id.LV_Favorite_Shows);
        mLv.addHeaderView(mHeader, null, false);

        mAdapter = new FavoriteAdapter(getActivity(), mCursor, this);
        mLv.setAdapter(mAdapter);
        return view;
    }

    public void onResume() {
        super.onResume();
        getLoaderManager().initLoader(0, null, new FavoriteLoaderCallback(getActivity(),this)).forceLoad();
    }

    public void clickFavorite(String slug) {
        Intent intent = new Intent(getActivity(), ShowDetailsActivity.class);
        intent.putExtra("SHOW", slug);
        startActivity(intent);
    }


    public void displayFavorite(Cursor cursor) {
        mCursor = cursor;
        if (cursor != null) {
            ((ImageView) mHeader.findViewById(R.id.IP_Favorite)).setImageResource(R.drawable.favorites_header_tv_happy);
        }

        mAdapter.swapCursor(cursor);
    }

}