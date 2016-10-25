package com.rsp.gankjoke.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.util.DateUtil;
import com.rsp.gankjoke.viewholder.ItemTechHolder;

import java.util.List;


/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class TechAdapter extends RecyclerView.Adapter<ItemTechHolder> {
    private List<GankItem> mData;
    private Context context;
    private OnItemClickListener listener;
    public TechAdapter(Context context, List<GankItem> mData) {
        this.context = context;
        this.mData = mData;
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
    @Override
    public ItemTechHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemTechHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(final ItemTechHolder holder, final int position) {
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position,holder);
                }
            }
        });
        String date = mData.get(position).publishedAt;
        int idx = date.indexOf(".");
        date = date.substring(0, idx).replace("T", " ");
        holder.getTime().setText(DateUtil.formatDateTime(date, true));
        holder.getTitle().setText(mData.get(position).desc);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
