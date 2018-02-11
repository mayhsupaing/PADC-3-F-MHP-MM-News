package com.mayhsupaing.news.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.models.LogInUserModel;
import com.mayhsupaing.news.delegates.LogInScreenDelegate;
import com.mayhsupaing.news.events.SuccessLoginEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

    private LogInScreenDelegate mLogInScreenDelegate;
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
        LogInUserModel.getsObjInstance(getContext()).logInUser(getContext(),phoneNo,password);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event){
        if(getActivity()!=null){
            getActivity().onBackPressed(); //Go Back to navigation view
        }
    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view){
        mLogInScreenDelegate.onTapToRegister();
    }

    /**
     * passed down host activity.
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mLogInScreenDelegate= (LogInScreenDelegate) context;
    }

    @OnClick(R.id.btn_login_with_google)
    public void onTapLoginWithGoogle(View view){
        mLogInScreenDelegate.onTapLogInWithGoogle();
    }
}
