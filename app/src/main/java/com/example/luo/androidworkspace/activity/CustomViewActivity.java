package com.example.luo.androidworkspace.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.luo.androidworkspace.R;

/**
 * Created by H_P on 2016/3/28.
 * @version 1.0
 * @author luo
 *
 */
public class CustomViewActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_view);
    }
}
