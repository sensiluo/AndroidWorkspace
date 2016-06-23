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
 *         效率最低的写法
 * @version 1.0
 */
public class CustomAdapter extends ArrayAdapter<User> {

    public CustomAdapter(Context context, List<User> users) {
        super(context, 0, users);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_custom_list, parent, false);
        TextView textView1 = (TextView) view.findViewById(R.id.text_view_custom1);
        TextView textView2 = (TextView) view.findViewById(R.id.text_view_custom2);
        textView1.setText(user.getmUserName());
        textView2.setText(user.getmAge() + "|" + user.getmPhoneNumber());
        return view;
    }
}
