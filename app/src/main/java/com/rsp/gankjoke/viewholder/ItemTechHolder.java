package com.rsp.gankjoke.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rsp.gankjoke.R;

public class ItemTechHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private TextView time;

    public View getView() {
        return view;
    }

    private View view;

    public ItemTechHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_tech, parent, false));
    }

    public ItemTechHolder(View view) {
        super(view);
        this.view = view;
        title = (TextView) view.findViewById(R.id.title);
        time = (TextView) view.findViewById(R.id.time);
    }

    public TextView getTime() {
        return time;
    }

    public TextView getTitle() {
        return title;
    }
}
