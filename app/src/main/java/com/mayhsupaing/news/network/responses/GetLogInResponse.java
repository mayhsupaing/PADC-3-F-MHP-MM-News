package com.mayhsupaing.news.network.responses;

import com.google.gson.annotations.SerializedName;
import com.mayhsupaing.news.data.vo.LogInUserVO;

import java.util.List;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class GetLogInResponse {

    private int code;
    private String message;

    @SerializedName("login_user")
    private LogInUserVO loginUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public LogInUserVO getLoginUser() {
        return loginUser;
    }
}
