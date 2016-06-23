package com.example.fragment.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.fragment.R;

/**
 * Created by H_P on 2016/5/5.
 *
 * @author luo
 * @version 1.0
 */
public class PageFragment extends Fragment {

    private static final String ARG_PAGE="ARG_PAGE";
    private int mPage;

    public static PageFragment newInstance(int page){
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);
        ((TextView)view).setText("Page->"+mPage);
        return view;
    }
}
