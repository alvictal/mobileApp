package com.example.next.firsapp.activity.fragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.util.FormatUtil;

import java.util.Date;


/**
 * Created by aluisio on 6/24/15.
 */
public class ShowDetailsFragment extends Fragment  {

    // Store instance variables
    private static Show show;
    private String title;
    private int page;

    // newInstance constructor for creating fragment with arguments
    public static ShowDetailsFragment newInstance(int page, String title, Show showC) {
        ShowDetailsFragment fragmentFirst = new ShowDetailsFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        fragmentFirst.setArguments(args);
        show =  showC;
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

        Date d = new Date(show.runtime()*1000);
        View view = inflater.inflate(R.layout.show_details, container, false);
        ((TextView) view.findViewById(R.id.TV_Show_Details_Summary)).setText(show.overview());
        ((TextView) view.findViewById(R.id.TV_Show_Details_Broadcasting)).
                setText("Broadcasting" + " : " + d.toString() + " " + show.network());
        ((TextView) view.findViewById(R.id.TV_Show_Details_Status)).
                setText("Status : " + show.status());
        ((TextView) view.findViewById(R.id.TV_Show_Details_Seasons)).
                setText("Seasons : " + show.airedEpisodes());
        ((TextView) view.findViewById(R.id.TV_Show_Details_Started)).
                setText("Started in : " + show.firstAired().substring(0,4));
        ((TextView) view.findViewById(R.id.TV_Show_Details_Country)).
                setText("Country : " + show.country().toUpperCase());
        ((TextView) view.findViewById(R.id.TV_Show_Details_Homepage)).
                setText("Homepage : " + show.trailer());

        return view;
    }


}
