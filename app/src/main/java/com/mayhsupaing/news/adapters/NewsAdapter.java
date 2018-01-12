package com.mayhsupaing.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.vo.NewsVO;
import com.mayhsupaing.news.delegates.NewsActionDelegate;
import com.mayhsupaing.news.viewholders.ItemNewsViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 12/3/2017.
 */

public class NewsAdapter extends RecyclerView.Adapter<ItemNewsViewHolder> {
    public NewsActionDelegate mNewsActionDelegate;

    private List<NewsVO> mNewsList;

    //constructor
    public NewsAdapter(NewsActionDelegate newsActionDelegate) {
        mNewsActionDelegate = newsActionDelegate;
        mNewsList = new ArrayList<>(); //Empty Array
    }

    @Override
    public ItemNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View newsItemView = inflater.inflate(R.layout.item_news, parent, false);
        ItemNewsViewHolder itemNewsViewHolder = new ItemNewsViewHolder(newsItemView, mNewsActionDelegate);
        return itemNewsViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemNewsViewHolder holder, int position) {
        holder.setNews(mNewsList.get(position));

    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }

    public void setNews(List<NewsVO> newsList) {
        mNewsList = newsList;
        notifyDataSetChanged();
    }
}
