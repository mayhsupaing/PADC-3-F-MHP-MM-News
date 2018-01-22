package com.mayhsupaing.news.data.models;

import com.mayhsupaing.news.data.vo.RegisterUserVO;
import com.mayhsupaing.news.events.SuccessLoginEvent;
import com.mayhsupaing.news.events.SuccessRegisterEvent;
import com.mayhsupaing.news.events.UserLogOutEvent;
import com.mayhsupaing.news.network.NewsDataAgent;
import com.mayhsupaing.news.network.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Lenovo on 1/22/2018.
 */

public class RegisterUserModel {

    private NewsDataAgent sDataAgent;

    private RegisterUserVO mRegisterUser;

    private static RegisterUserModel sObjInstance;

    private RegisterUserModel(){
        sDataAgent= RetrofitDataAgent.getsObjInstance();

        EventBus.getDefault().register(this);
    }

    public static RegisterUserModel getsObjInstance(){
        if(sObjInstance==null){
            sObjInstance=new RegisterUserModel();
        }
        return sObjInstance;
    }

    public void registerUser(String name,String password,String phoneNo){
        sDataAgent.registerUser(name, password, phoneNo);
    }

    public boolean isUserRegister() {

        return mRegisterUser != null;

    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onRegisterUserSuccess(SuccessRegisterEvent event) {
       mRegisterUser= event.getRegisterUserList(); //success register
    }

    public void logOut(){
        mRegisterUser=null;
        UserLogOutEvent event=new UserLogOutEvent();
        EventBus.getDefault().post(event);
    }
}
