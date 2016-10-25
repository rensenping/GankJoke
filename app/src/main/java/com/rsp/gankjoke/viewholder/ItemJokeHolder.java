package com.rsp.gankjoke.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.rsp.gankjoke.R;

public class ItemJokeHolder extends RecyclerView.ViewHolder {
    private TextView content;
    private TextView time;

    public ImageView getShare() {
        return share;
    }

    private ImageView share;

    public ItemJokeHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_joke, parent, false));
    }

    public ItemJokeHolder(View view) {
        super(view);
        content = (TextView) view.findViewById(R.id.content);
        time = (TextView) view.findViewById(R.id.time);
        share = (ImageView) view.findViewById(R.id.share);
    }

    public TextView getContent() {
        return content;
    }

    public TextView getTime() {
        return time;
    }
}
