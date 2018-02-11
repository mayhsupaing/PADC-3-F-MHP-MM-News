package com.mayhsupaing.news.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInApi;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.mayhsupaing.news.MMNewsApp;
import com.mayhsupaing.news.R;
import com.mayhsupaing.news.delegates.LogInScreenDelegate;
import com.mayhsupaing.news.fragments.LoginFragment;
import com.mayhsupaing.news.fragments.RegisterFragment;

import butterknife.ButterKnife;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class AccountControlActivity extends BaseActivity implements LogInScreenDelegate
        , GoogleApiClient.OnConnectionFailedListener {


    //define key
    private static final String IE_SCREEN_TYPE = "IE_SCREEN_TYPE";
    private static final int SCREEN_TYPE_LOGIN = 1;
    private static final int SCREEN_TYPE_REGISTER = 2;

    private GoogleApiClient mGoogleApiClient;


    //static factory button (for login)
    public static Intent newIntentLogIn(Context context) {
        Intent intent = new Intent(context, AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE, SCREEN_TYPE_LOGIN);
        return intent;
    }

    public static Intent newIntentRegister(Context context) {
        Intent intent = new Intent(context, AccountControlActivity.class);
        intent.putExtra(IE_SCREEN_TYPE, SCREEN_TYPE_REGISTER);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_control);
        ButterKnife.bind(this, this);

        int screeenType = getIntent().getIntExtra(IE_SCREEN_TYPE, -1);

        if (screeenType == SCREEN_TYPE_LOGIN) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, new LoginFragment())
                    .commit();
        } else if (screeenType == SCREEN_TYPE_REGISTER) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, new RegisterFragment())
                    .commit();
        }

        setUpGoogleApiClient();
    }


    /**
     * to navigate register screen.
     */
    @Override
    public void onTapToRegister() {
        getSupportFragmentManager().beginTransaction()
                .setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit)
                .replace(R.id.fl_container, new RegisterFragment())
                .addToBackStack("ToRegister")
                .commit();
    }

    //Google Service
    @Override
    public void onTapLogInWithGoogle() {
        Intent signInIntent=Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent,1000);
    }

    public void setUpGoogleApiClient() {
        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("496315112429-p36ln6dtksgcors441v7mt3uq4558nlh.apps.googleusercontent.com")
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(getApplicationContext())
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1000){
            GoogleSignInResult result=Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){
                GoogleSignInAccount account=result.getSignInAccount();
                Toast.makeText(getApplicationContext(), "Google Sign-In success : "
                        + account.getDisplayName(), Toast.LENGTH_SHORT).show();
            } else {
                // Google Sign-In failed
                Log.e(MMNewsApp.LOG_TAG, "Google Sign-In failed.");
                Toast.makeText(getApplicationContext(), "Google Sign-In failed.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}
