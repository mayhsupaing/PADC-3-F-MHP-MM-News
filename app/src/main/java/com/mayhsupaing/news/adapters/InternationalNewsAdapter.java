package com.mayhsupaing.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.viewholders.ItemInternationalNewsViewHolder;

/**
 * Created by Lenovo on 1/10/2018.
 */

public class InternationalNewsAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View newsInternational=inflater.inflate(R.layout.item_international_news,parent,false);
        ItemInternationalNewsViewHolder itemInternationalNewsViewHolder=new ItemInternationalNewsViewHolder(newsInternational);


        return itemInternationalNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
