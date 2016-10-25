package com.rsp.gankjoke.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rsp.gankjoke.R;

public class ItemGirlHolder extends RecyclerView.ViewHolder {
    private ImageView meizi;

    public View getView() {
        return view;
    }

    private View view;

    public ItemGirlHolder(LayoutInflater inflater, ViewGroup parent) {
        this(inflater.inflate(R.layout.item_girl, parent, false));
    }

    public ItemGirlHolder(View view) {
        super(view);
        this.view = view;
        meizi = (ImageView) view.findViewById(R.id.meizi);
    }

    public ImageView getMeizi() {
        return meizi;
    }
}
