package com.example.next.firsapp.activity;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.bumptech.glide.Glide;
import com.example.next.firsapp.R;
import com.example.next.firsapp.adapter.ShowFragmentAdapter;
import com.example.next.firsapp.core.FavoriteLoader;
import com.example.next.firsapp.database.manual.provider.SeriesTrackerContentProvider;
import com.example.next.firsapp.model.Favorite;
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.model.Season;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.presenter.ShowDetailsPresenter;
import com.example.next.firsapp.remote.service.OnClickSeasonListener;
import com.example.next.firsapp.view.FavoriteListener;
import com.example.next.firsapp.view.ShowDetailsView;

/**
 * Created by aluisio on 6/23/15.
 */
public class ShowDetailsActivity extends FragmentActivity implements ShowDetailsView, OnClickSeasonListener, FavoriteListener {

    private ShowFragmentAdapter adapterViewPager;
    private String screenshotUrl;
    private String showName = "game-of-thrones";
    private String mTitle;
    private Favorite mFavorite;
    private Integer mLastStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_header);
        setShowListener();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            showName = extras.getString("SHOW");
        }

        ShowDetailsPresenter showDetailsPresenter = new ShowDetailsPresenter(this,this);
        showDetailsPresenter.onShowDetailsRetrofit(showName);

    }

    public void setShowListener(){
        FloatingActionButton favoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite);

        favoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mFavorite == null){
                    setFavoriteShow(showName, mTitle, mLastStatus);
                } else {
                    setFavoriteShow(mFavorite.slug(), mFavorite.title(), mLastStatus);
                }
            }
        });
    }

    public void displayShow(Show show){
        screenshotUrl = show.images().poster().get(Images.ImageSize.THUMB);

        Glide.with(this).load(screenshotUrl).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().
                into((ImageView) findViewById(R.id.IP_Show_Details_Header_Preview));

        ((TextView) findViewById(R.id.TV_Show_Details_Year)).setText(show.year().toString());
        ((TextView) findViewById(R.id.TV_Show_Details_Note)).setText(show.rating().toString().subSequence(0, 3));

        ViewPager vpPager = (ViewPager) findViewById(R.id.vpPager);
        adapterViewPager = (new ShowFragmentAdapter(getSupportFragmentManager(), show, this));
        vpPager.setAdapter(adapterViewPager);

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(vpPager);

        getFavoriteShow(show);
    }

    public void getFavoriteShow(Show show){
        mTitle = show.title();
        getLoaderManager().initLoader(0, null, new FavoriteLoader(this,this, show.ids().slug(),show.title(),"query")).forceLoad();
    }

    public void setFavoriteShow(String slug, String title, Integer lastStatus) {
        if (lastStatus == 0) {
            getLoaderManager().initLoader(1, null, new FavoriteLoader(this, this, slug, title,"add")).forceLoad();
        } else if (lastStatus == 1) {
            getLoaderManager().initLoader(2, null, new FavoriteLoader(this,this,slug,title,"delete")).forceLoad();
        }

    }


    public void displayFavorite(Favorite favorite) {
        mFavorite = favorite;
        FloatingActionButton favoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite);
        if (favorite == null) {
            favoriteView.setImageResource(R.drawable.show_details_favorite_off);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_third));
            mLastStatus = 0;
        } else {
            favoriteView.setImageResource(R.drawable.show_details_favorite_on);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.default_color_first));
            mLastStatus = 1;
        }
    }


    @Override
    public void onClickSeason(Season season) {
        Intent intent = new Intent(getBaseContext(), SeasonDetailsActivity.class);
        intent.putExtra("SHOW", showName);
        intent.putExtra("SEASON", season.number());
        intent.putExtra("SEASONURL", season.images().poster().get(Images.ImageSize.FULL));
        intent.putExtra("SHOWURL",screenshotUrl);
        intent.putExtra("RATING", season.rating());
        startActivity(intent);
    }

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
