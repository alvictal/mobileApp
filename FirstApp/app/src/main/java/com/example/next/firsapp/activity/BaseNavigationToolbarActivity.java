package com.example.next.firsapp.activity;

import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.ViewGroup;


import com.example.next.firsapp.R;

/**
 * Created by movile on 05/07/15.
 */
public class BaseNavigationToolbarActivity extends BaseLoadingActivity {

    private ViewGroup mRoot;
    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        mRoot = (ViewGroup) layoutInflater.inflate(R.layout.base_toolbar_activity, null);
        ViewGroup content = (ViewGroup) mRoot.findViewById(R.id.base_navigation_toolbar_content);

        layoutInflater.inflate(layoutResID, content, true);
        super.setContentView(mRoot);
    }

    public void configureToolbar(String title) {
        mToolbar = (Toolbar) findViewById(R.id.base_navigation_drawer_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
