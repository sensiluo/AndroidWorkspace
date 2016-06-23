package com.example.fragment.views.adapters;

import android.support.v4.app.FragmentManager;

import com.example.fragment.fragments.FirstFragment;

/**
 * Created by H_P on 2016/5/6.
 *
 * @author luo
 * @version 1.0
 */
public class FirstFragmentPagerAdapter extends SmartFragmentStatePageAdapter {
    private static int NUM_ITEMS = 3;

    public FirstFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        return FirstFragment.newInstance(position, "PAGE->" + position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "Title:"+position;
    }

    @Override
    public float getPageWidth(int position) {
        return 0.93f;
    }
}
