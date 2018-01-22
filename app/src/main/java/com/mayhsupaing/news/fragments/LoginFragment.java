package com.mayhsupaing.news.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.activities.AccountControlActivity;
import com.mayhsupaing.news.activities.MainActivity;
import com.mayhsupaing.news.data.models.LogInUserModel;
import com.mayhsupaing.news.events.SuccessLoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import viewpods.BeforeLogInUserViewPod;
import viewpods.LogInUserViewPod;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class LoginFragment extends Fragment {

   /* private static final String CORRECT_EMAIL="abc@gmail.com";  //Constant attribute
    private static final String CORRECT_PASSWORD ="abcdef";*/

    @BindView(R.id.et_email_or_phone)
    EditText etEmailOrPhone;

    @BindView(R.id.et_password)
    EditText etPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_login,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_login)
    public void onTapLogin(View view){
        String phoneNo=etEmailOrPhone.getText().toString();
        String password=etPassword.getText().toString();
        LogInUserModel.getsObjInstance().logInUser(phoneNo,password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        if(getActivity()!=null){
            getActivity().onBackPressed(); //Go Back to navigation view
        }
    }

}
