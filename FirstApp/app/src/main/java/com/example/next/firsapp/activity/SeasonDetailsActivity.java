package com.example.next.firsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.next.firsapp.R;
import com.example.next.firsapp.adapter.EpisodeListAdapter;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.presenter.SeasonDetailsPresenter;
import com.example.next.firsapp.remote.service.OnClickEpisodeListener;
import com.example.next.firsapp.view.SeasonDetailsView;

import java.util.List;

public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView, OnClickEpisodeListener {
    private EpisodeListAdapter mAdapter;
    private String showName;
    private long seasonNumber;
    private String seasonImageUrl;
    private String showImageUrl;
    private Double rating;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            showName = extras.getString("SHOW");
            seasonNumber = extras.getLong("SEASON");
            seasonImageUrl = extras.getString("SEASONURL");
            showImageUrl = extras.getString("SHOWURL");
            rating = extras.getDouble("RATING");
        } else {
            showName = "sons-of-anarchy";
            seasonNumber = 3L;
        }

        setContentView(R.layout.season_details_list);

        configureToolbar("Season " + seasonNumber );

        SeasonDetailsPresenter seasonDetailsPresenter = new SeasonDetailsPresenter(this,this);

        View header = View.inflate(this, R.layout.season_details_header, null);

        ListView lv = (ListView) findViewById(R.id.LV_Season_Details_Episodes);
        lv.addHeaderView(header, null, false);
        mAdapter = new EpisodeListAdapter(this,this);
        lv.setAdapter(mAdapter);

        seasonDetailsPresenter.onSeasonDetailsRetrofit(showName, seasonNumber);

        this.populateHeader(header);



    }

    public void populateHeader(View view) {
        Glide.with(this).load(seasonImageUrl).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().
                into((ImageView) view.findViewById(R.id.IP_Season_Details));

        Glide.with(this).load(showImageUrl).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().
                into((ImageView) view.findViewById(R.id.IP_Season_Image));

        ((TextView) view.findViewById(R.id.TV_Season_Note)).setText(rating.toString().substring(0, 3));
    }



    public void onClickEpisodeL(Episode episode) {
        Intent intent = new Intent(getBaseContext(), EpisodeDetailsActivity.class);
        intent.putExtra("SHOW", showName);
        intent.putExtra("SEASON",episode.season());
        intent.putExtra("EPISODE",episode.number());
        startActivity(intent);
    }


    public void displaySeason(List<Episode> episode) {
        mAdapter.updateEpisodes(episode);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_season_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
