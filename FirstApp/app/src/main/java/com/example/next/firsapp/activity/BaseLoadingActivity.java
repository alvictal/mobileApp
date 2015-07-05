package com.example.next.firsapp.activity;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.next.firsapp.R;

/**
 * Created by movile on 05/07/15.
 */
public class BaseLoadingActivity extends AppCompatActivity {

    public void showLoading() {
        findViewById(R.id.base_loading_container).setVisibility(View.VISIBLE);
    }

    public void hideLoading() {
        findViewById(R.id.base_loading_container).setVisibility(View.GONE);
    }

}
