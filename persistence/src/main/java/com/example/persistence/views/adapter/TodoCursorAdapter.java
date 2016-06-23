package com.example.persistence.views.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.example.persistence.R;

/**
 * Created by H_P on 2016/4/28.
 * @author luo
 * @version 1.0
 */
public class TodoCursorAdapter extends CursorAdapter{
    public TodoCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    public TodoCursorAdapter(Context context, Cursor c, int flags) {
        super(context, c, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.item_todo,parent,false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tvBody = (TextView) view.findViewById(R.id.tvBody);
        TextView tvPriority = (TextView) view.findViewById(R.id.tvPriority);
        tvBody.setText(cursor.getString(cursor.getColumnIndexOrThrow("body")));
        tvPriority.setText(cursor.getInt(cursor.getColumnIndexOrThrow("priority"))+"");
    }
}
