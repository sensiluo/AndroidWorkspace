package com.example.screens.models;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.screens.R;


/**
 * Created by H_P on 2016/4/11.
 *
 * @author luo
 * @version 1.0
 */
public class HeterogenousTextViewHolder extends RecyclerView.ViewHolder {
    private TextView mName;
    private TextView mDetailInfo;

    public HeterogenousTextViewHolder(View itemView) {
        super(itemView);
        mName = (TextView) itemView.findViewById(R.id.text_name);
        mDetailInfo = (TextView) itemView.findViewById(R.id.text_detail_infomation);
    }

    public void setmName(TextView mName) {
        this.mName = mName;
    }

    public void setmDetailInfo(TextView mDetailInfo) {
        this.mDetailInfo = mDetailInfo;
    }

    public TextView getmName() {
        return mName;
    }

    public TextView getmDetailInfo() {
        return mDetailInfo;
    }
}
