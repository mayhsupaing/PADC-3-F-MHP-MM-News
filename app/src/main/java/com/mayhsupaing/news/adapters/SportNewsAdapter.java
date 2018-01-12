package com.mayhsupaing.news.adapters;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.viewholders.ItemSportNewsViewHolder;

import butterknife.BindView;

/**
 * Created by Lenovo on 1/8/2018.
 */

public class SportNewsAdapter extends RecyclerView.Adapter {
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View newsSportItemsView=inflater.inflate(R.layout.item_sport_news,parent,false);
        ItemSportNewsViewHolder itemSportNewsViewHolder=new ItemSportNewsViewHolder(newsSportItemsView);
        return itemSportNewsViewHolder;

        }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return 5;
    }
}
