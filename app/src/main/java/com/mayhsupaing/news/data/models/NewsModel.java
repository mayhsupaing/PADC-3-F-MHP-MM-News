package com.mayhsupaing.news.data.models;

import android.util.Log;

import com.mayhsupaing.news.MMNewsApp;
import com.mayhsupaing.news.data.vo.NewsVO;
import com.mayhsupaing.news.events.LoadedNewsEvent;
import com.mayhsupaing.news.network.HttpUrlConnectionDataAgent;
import com.mayhsupaing.news.network.NewsDataAgent;
import com.mayhsupaing.news.network.OKHttpDataAgent;
import com.mayhsupaing.news.network.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;

/**
 * Created by Lenovo on 12/23/2017.
 */

public class NewsModel {

    private NewsDataAgent sDataAgent;

    private static NewsModel sObjInstance;

    private Map<String, NewsVO> mNews;

    private NewsModel() {
        //sDataAgent= HttpUrlConnectionDataAgent.getsObjInstance();
        //sDataAgent= OKHttpDataAgent.getsObjInstance();
        sDataAgent = RetrofitDataAgent.getsObjInstance();

        mNews=new HashMap<>();

        EventBus.getDefault().register(this);
    }

    public static NewsModel getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new NewsModel();
        }
        return sObjInstance;
    }

    /**
     * load new from network api.
     */
    public void loadNews() {
        sDataAgent.loadNews();
    }

    /**
     * Get news object by Id.
     * @param newsId
     * @return
     */
    public NewsVO getNewsById(String newsId){
        return mNews.get(newsId);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onNewsLoaded(LoadedNewsEvent event) {
        for (NewsVO news:event.getNewsList()){
            mNews.put(news.getNewsId(),news);
        }
    }

}
