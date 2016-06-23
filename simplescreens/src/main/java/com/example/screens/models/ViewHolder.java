package com.example.screens.models;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.screens.R;

/**
 * Created by H_P on 2016/4/5.
 * 利用ViewHolder进一步解耦的优化listview
 */
public class ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;

    private ViewHolder(View convertView) {
        this.mViews = new SparseArray<View>();
        this.mConvertView = convertView;
        convertView.setTag(this);
    }

    public static ViewHolder getViewHolder(View convertView) {
        if(null==convertView.getTag())
            return new ViewHolder(convertView);
        return (ViewHolder) convertView.getTag();

    }

    public <T extends View> T getView(int resourceId) {
        View view = mViews.get(resourceId);
        if (view == null) {
            view = mConvertView.findViewById(resourceId);
            mViews.put(resourceId, view);
        }
        return (T) view;
    }


}
