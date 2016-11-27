package com.zhephyr.somedaytoday;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ChronoSwipeViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono_tab);

        ViewPager chronoPager = (ViewPager) findViewById(R.id.chrono_pager);
        chronoPager.setAdapter(new ChronoTabAdapter(getSupportFragmentManager(), ChronoSwipeViewActivity.this));
        chronoPager.setCurrentItem(0, true);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.chrono_tabs);
        tabLayout.setupWithViewPager(chronoPager);
    }
}