package com.example.screens.views.adapters.viewgroups;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.screens.R;
import com.example.screens.models.User;

/**
 * Created by H_P on 2016/4/6.
 *
 * @author luo
 * @version 1.0
 */
public class CustomLayout extends LinearLayout {
    private TextView mHead;
    private TextView mContent;

    public CustomLayout(Context context) {
        super(context);
        init(context);
    }

    public CustomLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public CustomLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void setInfo(User user) {
        mHead.setText(user.getmUserName());
        mContent.setText(user.getmAge() + "|" + user.getmPhoneNumber());
    }

    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutInflater.from(context).inflate(R.layout.item_custom_list_common, this, true);
        mHead = (TextView) findViewById(R.id.text_view_custom1);
        mContent = (TextView) findViewById(R.id.text_view_custom2);
    }

}
