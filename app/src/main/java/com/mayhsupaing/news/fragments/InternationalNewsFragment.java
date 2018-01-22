package com.mayhsupaing.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.adapters.InternationalNewsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 1/10/2018.
 */

public class InternationalNewsFragment extends Fragment {

    @BindView(R.id.rv_international_news)
    RecyclerView rvInternationalNews;

    private InternationalNewsAdapter mInternationalNewsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_international_news, container, false);
        ButterKnife.bind(this, view);


        mInternationalNewsAdapter = new InternationalNewsAdapter();
        LinearLayoutManager internationalNewsLinearLayoutManager = new LinearLayoutManager(getContext(),
                LinearLayoutManager.VERTICAL, true);
        rvInternationalNews.setLayoutManager(internationalNewsLinearLayoutManager);
        rvInternationalNews.setAdapter(mInternationalNewsAdapter);
        rvInternationalNews.setNestedScrollingEnabled(false);
/*
        GridLayoutManager manager = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);
        rvInternationalNews.setLayoutManager(manager);*/

        return view;

    }
}
