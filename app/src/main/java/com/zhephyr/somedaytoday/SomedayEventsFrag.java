package com.zhephyr.somedaytoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by wangz on 11/23/2016.
 */

public class SomedayEventsFrag extends Fragment {

    private String _title;
    private int _page;

    public static SomedayEventsFrag newInstance(int page, String title) {
        SomedayEventsFrag somedayEventsFrag = new SomedayEventsFrag();
        Bundle args = new Bundle();
        args.putInt("somedayPage", page);
        args.putString("somedayTitle", title);
        somedayEventsFrag.setArguments(args);
        return somedayEventsFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _page = getArguments().getInt("somedayPage", 0);
        _title = getArguments().getString("somedayTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        View view = inflator.inflate(R.layout.activity_someday_events, container, false);
        return view;
    }
}
