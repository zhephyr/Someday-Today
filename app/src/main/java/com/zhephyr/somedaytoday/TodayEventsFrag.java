package com.zhephyr.somedaytoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class TodayEventsFrag extends Fragment {

    private String _title;
    private int _page;

    public static TodayEventsFrag newInstance(int page, String title) {
        TodayEventsFrag todayEventsFrag = new TodayEventsFrag();
        Bundle args = new Bundle();
        args.putInt("todayPage", page);
        args.putString("todayTitle", title);
        todayEventsFrag.setArguments(args);
        return todayEventsFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _page = getArguments().getInt("todayPage", 0);
        _title = getArguments().getString("todayTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        View view = inflator.inflate(R.layout.activity_today_events, container, false);
        return view;
    }
}
