package com.jhonatanrocha.arcadiaquest.diceroller;

import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.jhonatanrocha.arcadiaquest.diceroller.adapter.TabAdapter;
import com.jhonatanrocha.arcadiaquest.diceroller.helper.SlidingTabLayout;

public class MainTabsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tabs);

        configureToolbar();
        configureAdapter();
        configureSlidingTabs();
    }

    protected void configureToolbar() {
        this.toolbar = (Toolbar) findViewById(R.id.toolbar);
        this.toolbar.setTitle("Arcadia Quest Dice Roller");
        this.toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.colorPrimary));
        setSupportActionBar(this.toolbar);
    }

    protected void configureAdapter() {
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        this.viewPager = (ViewPager) findViewById(R.id.vp_pagina);
        this.viewPager.setAdapter(tabAdapter);
    }

    protected void configureSlidingTabs() {
        this.slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        this.slidingTabLayout.setDistributeEvenly(true);
        this.slidingTabLayout.setViewPager(this.viewPager);
        this.slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));
    }


}
