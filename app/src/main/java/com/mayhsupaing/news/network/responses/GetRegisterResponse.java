package com.mayhsupaing.news.network.responses;

import com.google.gson.annotations.SerializedName;
import com.mayhsupaing.news.data.vo.LogInUserVO;
import com.mayhsupaing.news.data.vo.RegisterUserVO;

/**
 * Created by Lenovo on 1/22/2018.
 */

public class GetRegisterResponse {

    private int code;
    private String message;

    @SerializedName("login_user")
    private RegisterUserVO loginUser;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public RegisterUserVO getLoginUser() {
        return loginUser;
    }
}
