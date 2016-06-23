package com.example.screens.views.adapters.viewgroups;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.screens.R;
import com.example.screens.models.HeterogenousImageViewHolder;
import com.example.screens.models.HeterogenousTextViewHolder;
import com.example.screens.models.User;

import java.util.List;


/**
 * Created by H_P on 2016/4/11.
 *
 * @author luo
 * @version 1.0
 */
public class HeterogenousRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Object> mItems;
    private final int TEXT = 0, IMAGE = 1;

    public HeterogenousRecyclerAdapter(List<Object> list){
        this.mItems = list;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        Context context = parent.getContext();
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {

            case TEXT:
                view = layoutInflater.inflate(R.layout.item_text_viewholder, parent, false);
                viewHolder = new HeterogenousTextViewHolder(view);
                break;
            case IMAGE:
                view = layoutInflater.inflate(R.layout.item_image_viewholder, parent, false);
                viewHolder = new HeterogenousImageViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case TEXT:
                HeterogenousTextViewHolder viewHolder = (HeterogenousTextViewHolder) holder;
                User user = (User) mItems.get(position);
                if (user != null) {
                    viewHolder.getmName().setText("USER NAME:" + user.getmUserName());
                    viewHolder.getmDetailInfo().setText("AGE:" + user.getmAge());
                    break;
                }
            case IMAGE:
                HeterogenousImageViewHolder imageViewHolder = (HeterogenousImageViewHolder) holder;
                imageViewHolder.getImageView().setImageResource(R.drawable.logo_h);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object object = this.mItems.get(position);
        if (object instanceof User)
            return TEXT;

        if (object instanceof String)
            return IMAGE;
        return -1;
    }
}
