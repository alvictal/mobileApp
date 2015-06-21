package com.example.next.firsapp.activity;

import android.app.Activity;
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
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.presenter.SeasonDetailsPresenter;
import com.example.next.firsapp.view.SeasonDetailsView;

public class SeasonDetailsActivity extends Activity implements SeasonDetailsView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //    setContentView(R.layout.season_details_header);

        View header = View.inflate(this, R.layout.season_details_header, null);

        ListView view = (ListView) findViewById(R.id.LV_Season_Details_Episodes);
        view.addHeaderView(header,null,false);
        SeasonDetailsPresenter seasonDetailsPresenter = new SeasonDetailsPresenter(this,this);

    }

    public void displaySeason(Season season) {
        String screenShotUrl;
        String seasonShotUrl;

        screenShotUrl = season.images().screenshot().get(Images.ImageSize.THUMB);
        seasonShotUrl = season.images().poster().get(Images.ImageSize.THUMB);

        Glide.with(this).
                load(screenShotUrl).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().into((ImageView) findViewById(R.id.IP_Season_Details));

        Glide.with(this).
                load(seasonShotUrl).
                placeholder(R.drawable.season_details_show_placeholder).
                centerCrop().into((ImageView) findViewById(R.id.IP_Season_Image));

        ((TextView) findViewById(R.id.TV_Season_Year)).setText(season.airedEpisodes().toString());
        ((TextView) findViewById(R.id.TV_Season_Note)).setText(season.rating().toString());

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
