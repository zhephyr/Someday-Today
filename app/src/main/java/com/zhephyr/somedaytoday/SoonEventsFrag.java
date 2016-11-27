package com.zhephyr.somedaytoday;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class SoonEventsFrag extends Fragment {

    private String _title;
    private int _page;

    public static SoonEventsFrag newInstance(int page, String title) {
        SoonEventsFrag soonEventsFrag = new SoonEventsFrag();
        Bundle args = new Bundle();
        args.putInt("soonPage", page);
        args.putString("soonTitle", title);
        soonEventsFrag.setArguments(args);
        return soonEventsFrag;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        _page = getArguments().getInt("soonPage", 0);
        _title = getArguments().getString("soonTitle");
    }

    @Override
    public View onCreateView(LayoutInflater inflator, ViewGroup container, Bundle savedInstanceState) {
        View view = inflator.inflate(R.layout.activity_soon_events, container, false);
        return view;
    }
}
