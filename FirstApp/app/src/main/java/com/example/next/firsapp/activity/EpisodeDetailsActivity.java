package com.example.next.firsapp.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;


// --> Imports utilizados inicialmente
//import com.example.next.firsapp.core.ReadyOnlineContentClient;
//import com.example.next.firsapp.business.ApiConfiguration;
//import com.example.next.firsapp.remote.RemoteImageAsyncTask;

import com.bumptech.glide.Glide;
import com.example.next.firsapp.R;
import com.example.next.firsapp.model.Episode;
import com.example.next.firsapp.model.Images;
import com.example.next.firsapp.util.FormatUtil;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

//Usado inicialmente para aula com asynctask e interfaces
//public class EpisodeDetailsActivity extends Activity implements onListenerEpisode, onListenerBitmap {

public class EpisodeDetailsActivity extends Activity {

    private RestAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.episode_details_activity);

       // new ApiConfiguration(this);

       //  -> Usado para pegar json no próprio local
       // new ReadyContent(this).execute(this);

        // --> Usado para pegar o json remoto usando loader
        // getLoaderManager().initLoader(0, null, new ReadyOnlineContentClient(this,this)).forceLoad();

        // --> Usando agora retrofit para fazer a comunicação http e pegar os dados
        mAdapter = new RestAdapter.Builder().setEndpoint(this.getResources().getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails("silicon-valley", 2L, 3L, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                onEpisodedLoaded(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("retrofit", "Error to load http");
            }
        });
    }

    // Usado inicialmente como Interface para mostrar os detalhes do episódio
    public void onEpisodedLoaded(Episode episode) {


        String screenshotUrl = episode.images().screenshot().get(Images.ImageSize.THUMB);

        // Usado como interface para carregar a imagem pelo AsyncTask
       // new RemoteImageAsyncTask(this).execute(screenshotUrl);

        //Faças as coisas acontecerem
        Glide.with(this).load(screenshotUrl).
                placeholder(R.drawable.highlight_placeholder).
                centerCrop().
                into( (ImageView) findViewById(R.id.IP_Episode_Details));
        ((TextView) findViewById(R.id.TV_Episode_Details_Title)).setText(episode.title());
        ((TextView) findViewById(R.id.TV_Episode_Details_Summary)).setText(episode.overview());
        String firstAired = FormatUtil.formatDate(FormatUtil.formatDate(episode.firstAired())).toString();
        ((TextView) findViewById(R.id.TV_Episode_Details_Date)).setText(firstAired);

    }


    // Usado como interface para carregar a imagem pelo AsyncTask
    public void onBitmapLoaded(Bitmap bitmap) {
        //Faça a imagem aparacer
        ((ImageView) findViewById(R.id.IP_Episode_Details)).setImageBitmap(bitmap);
    }


    /*

    Código usado como exemplo para analisar o fluxograma de uma activity

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

        mTextView = (TextView) findViewById(R.id.TV_Episode_Details_Title);
        outState.putString("episodeName", mTextView.getText().toString());

        Log.d("EpisodeDetails", outState.getString("episodeName"));

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        episodeNameTest = savedInstanceState.getString("episodeName");

        Log.d("EpisodeDetails","Restore ");
    }*/

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
