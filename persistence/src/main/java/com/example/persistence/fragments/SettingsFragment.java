package com.example.persistence.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.example.persistence.R;

/**
 * Created by H_P on 2016/5/2.
 *
 * @author luo
 * @version 1.0
 */
public class SettingsFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
    }
}
