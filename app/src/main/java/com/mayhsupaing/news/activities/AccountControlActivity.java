package com.mayhsupaing.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.fragments.LoginFragment;
import com.mayhsupaing.news.fragments.RegisterFragment;

import butterknife.ButterKnife;
import viewpods.LogInUserViewPod;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class AccountControlActivity extends AppCompatActivity {


    //define key
    private static final String IE_SCREEN_TYPE="IE_SCREEN_TYPE";
    private static final int SCREEN_TYPE_LOGIN=1;
    private static final int SCREEN_TYPE_REGISTER=2;


    //static factory button (for login)
    public static Intent newIntentLogIn(Context context) {
        Intent intent = new Intent(context, AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE,SCREEN_TYPE_LOGIN);
        return intent;
    }

    public static Intent newIntentRegister(Context context) {
        Intent intent = new Intent(context, AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE,SCREEN_TYPE_REGISTER);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        ButterKnife.bind(this, this);

        int screeenType=getIntent().getIntExtra(IE_SCREEN_TYPE,-1);

        if(screeenType==SCREEN_TYPE_LOGIN)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container,new LoginFragment())
                    .commit();
        }
        else if(screeenType==SCREEN_TYPE_REGISTER)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container,new RegisterFragment())
                    .commit();
        }


    }



}
