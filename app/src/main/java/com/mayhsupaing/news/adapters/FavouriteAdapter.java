package com.mayhsupaing.news.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.vo.FavouriteVO;
import com.mayhsupaing.news.viewholders.FavouriteUserViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 2/4/2018.
 */

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteUserViewHolder> {

    private List<FavouriteVO> mFavouriteUser;

    public FavouriteAdapter() {
        mFavouriteUser=new ArrayList<>();
    }

    @Override
    public FavouriteUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context=parent.getContext();
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.item_user,parent,false);
        return new FavouriteUserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavouriteUserViewHolder holder, int position) {
        holder.setData(mFavouriteUser.get(position));
    }

    @Override
    public int getItemCount() {
        return mFavouriteUser.size();
    }

    public void setData(List<FavouriteVO> favouriteList){
        mFavouriteUser=favouriteList;
        notifyDataSetChanged();
    }
}
