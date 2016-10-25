package com.rsp.gankjoke.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsp.gankjoke.R;

public class ItemNewsHolder extends RecyclerView.ViewHolder {
    private TextView newsTitle;
    private ImageView newsImg;
    private TextView authorName;
    private TextView date;

    public View getView() {
        return view;
    }

    private View view;

    public ItemNewsHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_news, parent, false));
    }

    public ItemNewsHolder(View view) {
        super(view);
        this.view = view;
        newsTitle = (TextView) view.findViewById(R.id.news_title);
        newsImg = (ImageView) view.findViewById(R.id.news_img);
        authorName = (TextView) view.findViewById(R.id.author_name);
        date = (TextView) view.findViewById(R.id.date);
    }

    public TextView getDate() {
        return date;
    }

    public ImageView getNewsImg() {
        return newsImg;
    }

    public TextView getAuthorName() {
        return authorName;
    }

    public TextView getNewsTitle() {
        return newsTitle;
    }
}
