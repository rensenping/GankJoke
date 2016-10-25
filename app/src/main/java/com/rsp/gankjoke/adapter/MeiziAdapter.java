package com.rsp.gankjoke.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.rsp.gankjoke.entity.gank.GankItem;
import com.rsp.gankjoke.util.ScreenUtils;
import com.rsp.gankjoke.viewholder.ItemGirlHolder;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/19
 * version 1.0
 * 描述:
 */

public class MeiziAdapter extends RecyclerView.Adapter<ItemGirlHolder> {
    private Context context;
    private List<GankItem> mData;
    private OnItemClickListener listener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MeiziAdapter(Context context, List<GankItem> mData) {
        this.mData = mData;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return Math.round((float) ScreenUtils.getScreenWidth(context)/(mData.get(position).height*10f));
    }

    @Override
    public ItemGirlHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemGirlHolder(LayoutInflater.from(context), parent);
    }

    @Override
    public void onBindViewHolder(final ItemGirlHolder holder, final int position) {
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position, holder);
                }
            }
        });
        if (mData.get(holder.getAdapterPosition()).height > 0) {
            ViewGroup.LayoutParams layoutParams = holder.getMeizi().getLayoutParams();
            layoutParams.height = mData.get(holder.getAdapterPosition()).height;
    }
        Glide.with(context).load(mData.get(holder.getAdapterPosition()).url).asBitmap().into(new SimpleTarget<Bitmap>(ScreenUtils.getScreenWidth(context)/2, ScreenUtils.getScreenWidth(context)/2) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    ViewGroup.LayoutParams params = holder.getMeizi().getLayoutParams();
                    int width = resource.getWidth();
                    int height = resource.getHeight();
                    params.height = (ScreenUtils.getScreenWidth(context) / 2) * height / width;
                    mData.get(holder.getAdapterPosition()).height = resource.getHeight();
                    holder.getMeizi().setImageBitmap(resource);
                }

            }
        });
        //Glide.with(context).load(mData.get(position).url).centerCrop().into(holder.getMeizi());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

}
