package com.example.screens.views.adapters;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.screens.models.User;
import com.example.screens.views.adapters.viewgroups.CustomLayout;

import java.util.List;

/**
 * Created by H_P on 2016/4/6.
 *
 * @author luo
 * @version 1.0
 *          定制的adapter用于data和view之间的桥接
 */
public class CustomBaseAdapter extends BaseAdapter {
    private final List<User> mContents;
    private final Context context;

    public CustomBaseAdapter(Context context, List<User> contents) {
        super();
        this.context = context;
        this.mContents = contents;
    }

    @Override
    public int getCount() {
        return mContents.size();
    }

    @Override
    public Object getItem(int position) {
        return mContents.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CustomLayout view;
        if (null == convertView) {
            view = new CustomLayout(context);
            convertView = view;
        } else
            view = (CustomLayout) convertView;
        User user = (User) getItem(position);
//        Log.d("error",user.getmAge()+"");
        view.setInfo(user);
        return view;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
