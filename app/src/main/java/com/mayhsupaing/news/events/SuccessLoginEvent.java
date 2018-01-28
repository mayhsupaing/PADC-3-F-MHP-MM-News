package com.mayhsupaing.news.events;

import android.content.Context;

import com.mayhsupaing.news.data.vo.LogInUserVO;
import com.mayhsupaing.news.data.vo.NewsVO;

import java.util.List;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class SuccessLoginEvent {

    private LogInUserVO loginUserList;
    private Context context;

    public LogInUserVO getLoginUserList() {
        return loginUserList;
    }

    public Context getContext() {
        return context;
    }

    //constructor
    public SuccessLoginEvent(LogInUserVO loginUserList,Context context) {
        this.loginUserList = loginUserList;
        this.context=context;
    }
}
