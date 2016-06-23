package com.example.screens.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.screens.R;
import com.example.screens.models.User;
import com.example.screens.models.ViewHolder;

import java.util.List;

/**
 * Created by H_P on 2016/4/5.
 *
 * @author luo
 *         利用ViewHolder进一步解耦的优化listview
 * @version 1.0
 */
public class CustomAdapterViewHolder1 extends ArrayAdapter<User> {
    public CustomAdapterViewHolder1(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        if(null == convertView){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_custom_list,parent,false);
        }
        ViewHolder viewHolder = ViewHolder.getViewHolder(convertView);
        TextView textView1 = viewHolder.getView(R.id.text_view_custom1);
        TextView textView2 = viewHolder.getView(R.id.text_view_custom2);
        textView1.setText(user.getmUserName());
        textView2.setText(user.getmAge() + "|" + user.getmPhoneNumber());
        return convertView;
    }
}
