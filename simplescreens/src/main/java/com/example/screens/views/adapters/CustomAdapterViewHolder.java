package com.example.screens.views.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.screens.R;
import com.example.screens.models.User;

import java.util.List;

/**
 * Created by H_P on 2016/4/4.
 *
 * @author luo
 *         利用ViewHolder优化listview
 * @version 1.0
 */
public class CustomAdapterViewHolder extends ArrayAdapter<User> {

    public CustomAdapterViewHolder(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        User user = getItem(position);
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_custom_list, parent, false);
            viewHolder.head = (TextView) convertView.findViewById(R.id.text_view_custom1);
            viewHolder.content = (TextView) convertView.findViewById(R.id.text_view_custom2);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.head.setText(user.getmUserName());
        viewHolder.content.setText(user.getmAge() + "|" + user.getmPhoneNumber());
        return convertView;
    }

    class ViewHolder {
        private TextView head;
        private TextView content;
    }
}
