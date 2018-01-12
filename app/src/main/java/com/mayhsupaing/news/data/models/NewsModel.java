package com.mayhsupaing.news.data.models;

import com.mayhsupaing.news.network.HttpUrlConnectionDataAgent;
import com.mayhsupaing.news.network.NewsDataAgent;
import com.mayhsupaing.news.network.OKHttpDataAgent;
import com.mayhsupaing.news.network.RetrofitDataAgent;

import okhttp3.OkHttpClient;

/**
 * Created by Lenovo on 12/23/2017.
 */

public class NewsModel {

    private NewsDataAgent sDataAgent;

    private static NewsModel sObjInstance;

    private NewsModel(){
        //sDataAgent= HttpUrlConnectionDataAgent.getsObjInstance();
        //sDataAgent= OKHttpDataAgent.getsObjInstance();
        sDataAgent= RetrofitDataAgent.getsObjInstance();
    }

    public static NewsModel getsObjInstance(){
        if(sObjInstance==null)
        {
            sObjInstance=new NewsModel();
        }
        return sObjInstance;
    }

    /**
     *load new from network api.
     */
    public void loadNews(){
        sDataAgent.loadNews();
    }
}
