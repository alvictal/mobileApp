package com.example.next.firsapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.adapter.ShowListAdapter;
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.model.Show;
import com.example.next.firsapp.presenter.ShowListPresenter;
import com.example.next.firsapp.remote.service.OnClickShowListener;
import com.example.next.firsapp.view.ShowDetailsView;
import com.example.next.firsapp.view.ShowListView;

import java.util.List;

/**
 * Created by movile on 27/06/15.
 */
public class MainShowActivity extends Activity implements ShowListView, OnClickShowListener {
    private ShowListAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.show_gridview);

        ShowListPresenter showListPresenter = new ShowListPresenter(this,this);
        GridView gv = (GridView) findViewById(R.id.show_gridView);
        mAdapter = new ShowListAdapter(this,this);
        gv.setAdapter(mAdapter);

        showListPresenter.onShowListRetrofit();
    }

    public void displayShowList(List<Show> shows){
        mAdapter.updateShow(shows);
    }

    public void onClickShow(Show show) {
        Intent intent = new Intent(getBaseContext(), ShowDetailsActivity.class);
        intent.putExtra("SHOW", show.ids().slug());
        startActivity(intent);

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
