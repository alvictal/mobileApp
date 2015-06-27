package com.example.next.firsapp.activity.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.next.firsapp.R;
import com.example.next.firsapp.adapter.RecyclerAdapter;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.presenter.ShowSeasonListPresenter;
import com.example.next.firsapp.remote.service.OnClickSeasonListener;
import com.example.next.firsapp.view.ShowSeasonListView;

import java.util.Collections;
import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class ShowSeasonFragment extends Fragment implements ShowSeasonListView {
    private static Show show;
    private RecyclerAdapter mAdapter;
    private static OnClickSeasonListener mListener;
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static ShowSeasonFragment newInstance(int page, String title, Show showC, OnClickSeasonListener onClickSeasonListener) {
        ShowSeasonFragment fragmentFirst = new ShowSeasonFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);

        show =  showC;
        mListener = onClickSeasonListener;
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_recycler_layout , container, false);
        configureRecyclerView(view);
        return view;
    }

    private void configureRecyclerView(View view) {
        RecyclerView rView = (RecyclerView) view.findViewById(R.id.horizontal_recycler);
        rView.setLayoutManager(new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));

        mAdapter = new RecyclerAdapter(this.getActivity(), R.layout.season, mListener);
        rView.setAdapter(mAdapter);

        populateRecyclerView();
    }

    private void populateRecyclerView() {
        ShowSeasonListPresenter showSeasonListPresenter = new ShowSeasonListPresenter(this.getActivity(),this);
        showSeasonListPresenter.onSeasonDetailsRetrofit(show.ids().slug());
    }

    public void displayShowSeasonList(List<Season> seasons){
        Collections.reverse(seasons);
        mAdapter.updateContents(seasons);
    }

}
