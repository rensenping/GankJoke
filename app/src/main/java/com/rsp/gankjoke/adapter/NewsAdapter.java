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
import com.rsp.gankjoke.entity.news.NewsItem;
import com.rsp.gankjoke.util.ScreenUtils;
import com.rsp.gankjoke.viewholder.ItemNewsHolder;

import java.util.List;

/**
 * @author 小任
 * @date 2016/10/20
 * version 1.0
 * 描述:
 */

public class NewsAdapter extends RecyclerView.Adapter<ItemNewsHolder> {
    private Context context;
    private List<NewsItem> mData;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    private OnItemClickListener listener;

    public NewsAdapter(Context conext, List<NewsItem> mData)  {
        this.context = conext;
        this.mData = mData;
    }
    @Override
    public ItemNewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ItemNewsHolder(LayoutInflater.from(context),parent);
    }
    @Override
    public int getItemViewType(int position) {
        return Math.round((float) ScreenUtils.getScreenWidth(context)/(mData.get(position).height*10f));
    }
    @Override
    public void onBindViewHolder(final ItemNewsHolder holder, final int position) {
        holder.getView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position,holder);
                }
            }
        });
        NewsItem newsItem = mData.get(position);
        if (newsItem.height>0) {
            ViewGroup.LayoutParams params = holder.getNewsImg().getLayoutParams();
            params.height = newsItem.height;
        }
        holder.getAuthorName().setText("来自:"+newsItem.author_name);
        holder.getDate().setText(newsItem.date);
        holder.getNewsTitle().setText(newsItem.title);
        Glide.with(context).load(newsItem.thumbnail_pic_s).asBitmap().into(new SimpleTarget<Bitmap>(ScreenUtils.getScreenWidth(context)/2,ScreenUtils.getScreenWidth(context)/2) {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                if (holder.getAdapterPosition() != RecyclerView.NO_POSITION) {
                    ViewGroup.LayoutParams params = holder.getNewsImg().getLayoutParams();
                    int width = resource.getWidth();
                    int height = resource.getHeight();
                    params.height = (ScreenUtils.getScreenWidth(context) / 2) * height / width;
                    mData.get(holder.getAdapterPosition()).height = resource.getHeight();
                    holder.getNewsImg().setImageBitmap(resource);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }
}
