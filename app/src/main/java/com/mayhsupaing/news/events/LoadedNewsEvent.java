package com.mayhsupaing.news.events;

import com.mayhsupaing.news.data.vo.NewsVO;

import java.util.List;

/**
 * Created by Lenovo on 12/24/2017.
 */

public class LoadedNewsEvent {

    private List<NewsVO> newsList;

    public LoadedNewsEvent(List<NewsVO> newsList) {
        this.newsList = newsList;
    }

    public List<NewsVO> getNewsList() {
        return newsList;
    }
}
