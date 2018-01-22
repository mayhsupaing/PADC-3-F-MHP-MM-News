package com.mayhsupaing.news.events;

import com.mayhsupaing.news.data.vo.RegisterUserVO;

/**
 * Created by Lenovo on 1/22/2018.
 */

public class SuccessRegisterEvent {

    private RegisterUserVO registerUserList;

    public SuccessRegisterEvent(RegisterUserVO registerUserList) {
        this.registerUserList = registerUserList;
    }

    public RegisterUserVO getRegisterUserList() {
        return registerUserList;
    }
}
