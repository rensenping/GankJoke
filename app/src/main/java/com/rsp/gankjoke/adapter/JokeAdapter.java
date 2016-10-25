package com.rsp.gankjoke.adapter;

import android.content.Context;
import android.print.PrintAttributes;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.gankjoke.entity.joke.JokeItem;
import com.rsp.gankjoke.util.DateUtil;
import com.rsp.gankjoke.util.ShareUtil;
import com.rsp.gankjoke.viewholder.ItemJokeHolder;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/21
 * version 1.0
 * 描述:
 */

public class JokeAdapter extends RecyclerView.Adapter<ItemJokeHolder> {
    private Context context;
    private List<JokeItem> mData;

    public JokeAdapter(Context context, List<JokeItem> mData) {
        this.context = context;
        this.mData = mData;
    }

    @Override
    public ItemJokeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemJokeHolder(LayoutInflater.from(context),parent);
    }

    @Override
    public void onBindViewHolder(ItemJokeHolder holder, final int position) {
        holder.getContent().setText(Html.fromHtml(mData.get(position).content));
        holder.getTime().setText(DateUtil.timestamp2Date(mData.get(position).unixtime));
        holder.getShare().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtil.shareText(context,Html.fromHtml(mData.get(position).content).toString(),"笑话分享");
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
