package com.example.next.firsapp.activity;

import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.next.firsapp.R;
import com.example.next.firsapp.core.ReadyContent;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.util.FormatUtil;


public class EpisodeDetailsActivity extends Activity implements onListenerEpisode {

    TextView mTextView;
    String episodeNameTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
        Log.d("EpisodeDetails", "onCreate");

        new ReadyContent(this).execute(this);

    }

    public void onEpisodedLoaded(Episode episode) {
        //Faças as coisas acontecerem
        mTextView = (TextView) findViewById(R.id.textViewTitle);
        mTextView.setText(episode.title());

        mTextView = (TextView) findViewById(R.id.textViewEpisodeDetailsSummary);
        mTextView.setText(episode.overview());

        mTextView = (TextView) findViewById(R.id.textViewDate);
        mTextView.setText(new FormatUtil().formatDate(episode.firstAired()).toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("EpisodeDetails", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("EpisodeDetails", "onResume");

        if (episodeNameTest != null) {
            Log.d("EpisodeDetailstext", episodeNameTest);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("EpisodeDetails", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("EpisodeDetails", "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("EpisodeDetails", "onDestroy");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.episode_details_menu, menu);
        return true;
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {

        mTextView = (TextView) findViewById(R.id.textViewTitle);
        outState.putString("episodeName", mTextView.getText().toString());

        Log.d("EpisodeDetails", outState.getString("episodeName"));

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        episodeNameTest = savedInstanceState.getString("episodeName");

        Log.d("EpisodeDetails","Restore ");
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
