package com.mayhsupaing.news.data.models;

import com.mayhsupaing.news.data.vo.LogInUserVO;
import com.mayhsupaing.news.events.SuccessLoginEvent;
import com.mayhsupaing.news.events.UserLogOutEvent;
import com.mayhsupaing.news.network.NewsDataAgent;
import com.mayhsupaing.news.network.RetrofitDataAgent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class LogInUserModel {

    private NewsDataAgent sDataAgent;

    private static LogInUserModel sObjInstance;

    private LogInUserVO mLogInUser;

    private LogInUserModel() {
        sDataAgent = RetrofitDataAgent.getsObjInstance();
        EventBus.getDefault().register(this); //Register Listen (For Listen)
    }

    public static LogInUserModel getsObjInstance() {
        if (sObjInstance == null) {             //factory logic
            sObjInstance = new LogInUserModel();
        }
        return sObjInstance;
    }

    public void logInUser(String phoneNo, String password) {
        sDataAgent.loginUser(phoneNo, password);
    }

    //check whether user is login or not.
    public boolean isUserLogIn() {

        return mLogInUser != null;

        /*if(mLogInUser==null){
            return  false;
        }
        else
            return true;*/
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onLoginUserSuccess(SuccessLoginEvent event) {
        mLogInUser = event.getLoginUserList(); //success login
    }

    public void logOut(){
        mLogInUser=null;
        UserLogOutEvent event=new UserLogOutEvent();
        EventBus.getDefault().post(event);
    }
}
