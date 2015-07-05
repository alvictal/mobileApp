package com.example.next.firsapp.activity;

import android.content.res.Configuration;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.ActionBarDrawerToggle;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.next.firsapp.R;
import com.example.next.firsapp.activity.fragment.FavoriteFragment;

/**
 * Created by movile on 05/07/15.
 */
public class BaseNavigationDrawerActivity extends BaseLoadingActivity {

    private ViewGroup mRoot;
    protected DrawerLayout mDrawerLayout;
    protected ActionBarDrawerToggle mDrawerToggle;
    protected Toolbar mToolbar;

    @Override
    public void setContentView(int layoutResID) {
        LayoutInflater layoutInflater = getLayoutInflater();

        mRoot = (ViewGroup) layoutInflater.inflate(R.layout.base_navigation_drawer_activity, null);
        ViewGroup content = (ViewGroup) mRoot.findViewById(R.id.base_navigation_content);

        layoutInflater.inflate(layoutResID, content, true);
        super.setContentView(mRoot);
    }

    public void configureNavigation() {
        configureToolbar();
        configureNavigationDrawer();
        configureNavigationDrawerContent();
    }

    private void configureNavigationDrawerContent() {
        getSupportFragmentManager().beginTransaction().add(R.id.base_navigation_drawer_content, new FavoriteFragment(), "favorites").commit();
    }

    private void configureNavigationDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.base_navigation_drawer_container);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.base_navigation_drawer_open, R.string.base_navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void configureToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.base_navigation_drawer_toolbar);
        setSupportActionBar(mToolbar);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

}
