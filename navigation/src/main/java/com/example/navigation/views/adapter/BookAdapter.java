package com.example.navigation.views.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.navigation.R;
import com.example.navigation.model.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by H_P on 2016/4/25.
 *
 * @author luo
 * @version 1.0
 */
public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> bookList) {
        super(context, 0, bookList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Book book = getItem(position);
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_book, parent, false);
            viewHolder.bookCover = (ImageView) convertView.findViewById(R.id.ivBookCover);
            viewHolder.bookAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            viewHolder.bookTitle = (TextView) convertView.findViewById(R.id.tvTitle);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.bookAuthor.setText(book.getAuthor());
        viewHolder.bookTitle.setText(book.getTitle());
        Picasso.with(getContext()).load(Uri.parse(book.getCoverUrl())).error(R.drawable.ic_nocover).into(viewHolder.bookCover);
        return convertView;
    }

    private static class ViewHolder {
        public ImageView bookCover;
        public TextView bookTitle;
        public TextView bookAuthor;
    }
}
