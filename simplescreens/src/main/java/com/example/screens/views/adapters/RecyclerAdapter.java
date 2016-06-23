package com.example.screens.views.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.screens.R;
import com.example.screens.models.User;

import java.util.List;


/**
 * Created by H_P on 2016/4/10.
 *
 * @author luo
 * @version 1.0
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private static List<User> mUsers;
    public static OnSelfDefineClickListener listener;

    public RecyclerAdapter(List<User> users) {
        this.mUsers = users;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item_recycler_view, parent, false);
        RecyclerAdapter.ViewHolder viewHolder = new RecyclerAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {
        User user = mUsers.get(position);
        TextView textView = holder.textView;
        Button button = holder.button;
        textView.setText(user.getmUserName());
        button.setText(user.getmAge() + "");
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    //    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//
//        public TextView textView;
//        public Button button;
//        private Context context;
//
//        public ViewHolder(Context context,View itemView) {
//            super(itemView);
//            this.textView = (TextView) itemView.findViewById(R.id.contact_name);
//            this.button = (Button) itemView.findViewById(R.id.message_button);
//            this.context = context;
//            this.button.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            int position = getLayoutPosition();
//            User user =mUsers.get(position);
//            Toast.makeText(this.context,user.getmUserName(),Toast.LENGTH_SHORT).show();
//        }
//    }

    public interface OnSelfDefineClickListener {
        public void onItemClick(View itemView, int position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Button button;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.textView = (TextView) itemView.findViewById(R.id.contact_name);
            this.button = (Button) itemView.findViewById(R.id.message_button);
            this.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null)
                        RecyclerAdapter.listener.onItemClick(itemView, getLayoutPosition());
                }
            });
        }
    }

    public static void setListener(OnSelfDefineClickListener listener) {
        RecyclerAdapter.listener = listener;
    }
}
