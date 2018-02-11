package com.mayhsupaing.news.services;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import com.mayhsupaing.news.MMNewsApp;

import java.util.Date;

/**
 * Created by Lenovo on 2/10/2018.
 */

public class SampleService extends IntentService {

    private static final String IE_TIMESTAMP="timeStamp";

    public SampleService() {
        super("SampleService");
    }

    //static factory pattern
    public static Intent newIntent(Context context,String timeStamp){
        Intent intent=new Intent(context,SampleService.class);
        intent.putExtra(IE_TIMESTAMP,new Date().toString());
        return intent;
    }

    //bg ka logic ko implement lote
    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String timeStamp="";
        //retrieve data
        if(intent!=null){
            timeStamp= intent.getStringExtra(IE_TIMESTAMP);
        }
        Log.d(MMNewsApp.LOG_TAG,"SampleService : onHandleIntent"+timeStamp);

    }
}
