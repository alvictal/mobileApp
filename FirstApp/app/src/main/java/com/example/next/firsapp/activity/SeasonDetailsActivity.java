package com.example.next.firsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
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
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.presenter.SeasonDetailsPresenter;
import com.example.next.firsapp.remote.service.OnClickListener;
import com.example.next.firsapp.view.SeasonDetailsView;

import java.util.List;

public class SeasonDetailsActivity extends Activity implements SeasonDetailsView, OnClickListener {
    private EpisodeListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.season_details_list);

        SeasonDetailsPresenter seasonDetailsPresenter = new SeasonDetailsPresenter(this,this);

        View header = View.inflate(this, R.layout.season_details_header, null);

        ListView lv = (ListView) findViewById(R.id.LV_Season_Details_Episodes);
        lv.addHeaderView(header, null, false);
        mAdapter = new EpisodeListAdapter(this,this);
        lv.setAdapter(mAdapter);

        seasonDetailsPresenter.onSeasonDetailsRetrofit("silicon-valley",2L);
    }


    public void onClickEpisodeL(Episode episode) {
        Intent intent = new Intent(getBaseContext(), EpisodeDetailsActivity.class);
        intent.putExtra("SHOW", "silicon-valley");
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
