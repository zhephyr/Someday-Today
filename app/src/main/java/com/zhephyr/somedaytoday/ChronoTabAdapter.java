package com.zhephyr.somedaytoday;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ChronoTabAdapter extends FragmentPagerAdapter {
    private static int NUM_ITEMS = 3;

    public ChronoTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TodayEventsFrag.newInstance(0, "Today");
            case 1:
                return SoonEventsFrag.newInstance(1, "Soon");
            case 2:
                return SomedayEventsFrag.newInstance(2, "Someday");
            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Top Indicator?";
    }
}