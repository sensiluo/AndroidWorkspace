package com.example.luo.interaction.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luo.interaction.R;

/**
 * Created by H_P on 2016/4/17.
 *
 * @author luo
 * @version 1.0
 */
public class DemoObjectFragment extends Fragment {
    public static final String ARG_OBJ = "object";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_collection_object, container, false);
        Bundle args = getArguments();
        ((TextView) rootView.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJ)));
        return rootView;
    }
}
