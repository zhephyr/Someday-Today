package com.zhephyr.somedaytoday;

import android.os.Bundle;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class ChronoSwipeViewActivity extends AppCompatActivity {
    FragmentPagerAdapter adapterChronoPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chrono_tab);
        ViewPager chronoPager = (ViewPager) findViewById(R.id.chrono_pager);
        adapterChronoPager = new ChronoTabAdapter(getSupportFragmentManager());
        chronoPager.setAdapter(adapterChronoPager);
        chronoPager.setCurrentItem(0, true);
    }
}