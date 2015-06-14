package com.example.next.firsapp.remote;

import android.content.Context;
import android.util.Log;

import com.example.next.firsapp.R;
import com.example.next.firsapp.model.converter.ModelConverter;
import com.example.next.firsapp.model.Episode;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by movile on 14/06/15.
 */
public class FetchRemoteEpisodeDetails {
    private static final String TAG = FetchRemoteEpisodeDetails.class.getSimpleName();

    public Episode get(Context context, String url) {
        Episode episode = null;
        InputStreamReader reader = null;
        try {
            HttpURLConnection connection = configureConnection(context, url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e("FetchRemoteEpisodeDetails", "Error loading remote content", e);
        } finally {
            // Release InputStreamReader if used
            if (reader != null) {
                try {
                    reader.close();;
                } catch (IOException e) {
                    Log.e(TAG, "Error releasing resource", e);
                }
            }

        }

        return episode;
    }

    private HttpURLConnection configureConnection (Context context, String url) {
        HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

        connection.setReadTimeout(context.getResources().getString(R.string.api_timeout_read));
        connection.setConnectTimeout(CONNECTION_TIMEOUT);
        connection.setRequestMethod("GET");
        connection.setDoInput(true);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("trakt-api-version", API_VERSION);
        connection.setRequestProperty("trakt-api-key", API_KEY);
    }
}

