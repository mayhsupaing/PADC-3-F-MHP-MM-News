package com.mayhsupaing.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.adapters.SportNewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 1/7/2018.
 */

public class SportNewsFragment extends Fragment {

    @BindView(R.id.rv_sport_news)
    RecyclerView rvSportNews;

    private SportNewsAdapter mSportNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sport_news, container, false);
        ButterKnife.bind(this, view);

        mSportNewsAdapter = new SportNewsAdapter();
        LinearLayoutManager sportNewsLinearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, true);
        rvSportNews.setLayoutManager(sportNewsLinearLayoutManager);
        rvSportNews.setAdapter(mSportNewsAdapter);
        return view;

    }
}
