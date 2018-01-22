package com.mayhsupaing.news.events;

import com.mayhsupaing.news.data.vo.LogInUserVO;
import com.mayhsupaing.news.data.vo.NewsVO;

import java.util.List;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class SuccessLoginEvent {

    private LogInUserVO loginUserList;

    public LogInUserVO getLoginUserList() {
        return loginUserList;
    }

    public SuccessLoginEvent(LogInUserVO loginUserList) {
        this.loginUserList = loginUserList;
    }
}
