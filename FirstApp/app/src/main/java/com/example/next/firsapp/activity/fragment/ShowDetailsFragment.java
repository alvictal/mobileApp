package com.example.next.firsapp.activity.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Html;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.activity.ShowDetailsActivity;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.presenter.ShowDetailsPresenter;
import com.example.next.firsapp.view.ShowDetailsView;

/**
 * Created by aluisio on 6/24/15.
 */
public class ShowDetailsFragment extends Fragment implements ShowDetailsView {

    // Store instance variables
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static ShowDetailsFragment newInstance(int page, String title) {
        ShowDetailsFragment fragmentFirst = new ShowDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt("someInt", 0);
        title = getArguments().getString("someTitle");

        ShowDetailsPresenter showDetailsPresenter = new ShowDetailsPresenter(this.getActivity(),this);
        showDetailsPresenter.onShowDetailsRetrofit("silicon-valley");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_details, container, false);

        return view;
    }

    public void displayShow(Show show){
        ShowDetailsActivity showDetailsActivity = (ShowDetailsActivity) this.getActivity();
        showDetailsActivity.displayHeader(show);

        ((TextView) getView().findViewById(R.id.TV_Show_Details_Summary)).setText(show.overview());
        ((TextView) getView().findViewById(R.id.TV_Show_Details_Broadcasting)).
                setText("Broadcasting" + " : " + show.runtime().toString() + " " + show.network());
        ((TextView) getView().findViewById(R.id.TV_Show_Details_Status)).
                setText("Status : " + show.status());
        ((TextView) getView().findViewById(R.id.TV_Show_Details_Seasons)).
                setText("Seasons : " + show.language());
        ((TextView) getView().findViewById(R.id.TV_Show_Details_Started)).
                setText("Started in : " + show.firstAired().substring(0,4));
        ((TextView) getView().findViewById(R.id.TV_Show_Details_Country)).
                setText("Country : " + show.country());
        ((TextView) getView().findViewById(R.id.TV_Show_Details_Homepage)).
                setText("Homepage : " + show.trailer());

    }

}
