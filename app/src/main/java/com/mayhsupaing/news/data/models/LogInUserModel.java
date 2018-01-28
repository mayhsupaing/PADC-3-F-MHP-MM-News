package com.mayhsupaing.news.data.models;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.vo.LogInUserVO;
import com.mayhsupaing.news.events.SuccessLoginEvent;
import com.mayhsupaing.news.events.UserLogOutEvent;
import com.mayhsupaing.news.network.NewsDataAgent;
import com.mayhsupaing.news.network.RetrofitDataAgent;
import com.mayhsupaing.news.utils.AppConstants;
import com.mayhsupaing.news.viewpods.LogInUserViewPod;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class LogInUserModel {

    private NewsDataAgent sDataAgent;

    private static LogInUserModel sObjInstance;

    private LogInUserVO mLogInUser;


    //constructor(initialize logic for object)
    private LogInUserModel(Context context) {
        sDataAgent = RetrofitDataAgent.getsObjInstance();


        EventBus.getDefault().register(this); //Register Listen (For Listen)

        SharedPreferences sharedPreferences = context.getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,
                Context.MODE_PRIVATE);

        int logInUserId = sharedPreferences.getInt(AppConstants.LOGIN_USER_ID_KEY, -1);
        if (logInUserId != -1) {
            //user has already login.
            String name = sharedPreferences.getString(AppConstants.LOGIN_USER_EMAIL_KEY, null);
            String email = sharedPreferences.getString(AppConstants.LOGIN_USER_EMAIL_KEY, null);
            String phoneNo = sharedPreferences.getString(AppConstants.LOGIN_USER_PHONENO_KEY, null);
            String profileUrl = sharedPreferences.getString(AppConstants.LOGIN_USER_PROFILEURL_KEY, null);
            String coverUrl = sharedPreferences.getString(AppConstants.LOGIN_USER_COVERURL_KEY, null);


            mLogInUser = new LogInUserVO(logInUserId, name, email, phoneNo, profileUrl, coverUrl);


        }
    }


    public static LogInUserModel getsObjInstance(Context context) {
        if (sObjInstance == null) {             //factory logic
            sObjInstance = new LogInUserModel(context);
        }
        return sObjInstance;
    }

    public void logInUser(Context context, String phoneNo, String password) {
        sDataAgent.loginUser(context, phoneNo, password);
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


        //Save user data in SharedPreferences.
        SharedPreferences sharedPreferences = event.getContext().getSharedPreferences(AppConstants.LOGIN_USER_DATA_SP,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(AppConstants.LOGIN_USER_ID_KEY, event.getLoginUserList().getUserId());
        editor.putString(AppConstants.LOGIN_USER_NAME_KEY, event.getLoginUserList().getName());
        editor.putString(AppConstants.LOGIN_USER_EMAIL_KEY, event.getLoginUserList().getEmail());
        editor.putString(AppConstants.LOGIN_USER_PHONENO_KEY, event.getLoginUserList().getPhoneNo());
        editor.putString(AppConstants.LOGIN_USER_PROFILEURL_KEY, event.getLoginUserList().getProfileUrl());
        editor.putString(AppConstants.LOGIN_USER_COVERURL_KEY, event.getLoginUserList().getCoverUrl());

        editor.apply(); // save data through background thread without affecting performance.
        /*editor.commit(); *///save data immediately if we save and want to retrieve, use commit.


    }

    public void logOut() {
        mLogInUser = null;
        UserLogOutEvent event = new UserLogOutEvent();
        EventBus.getDefault().post(event);
    }
}
