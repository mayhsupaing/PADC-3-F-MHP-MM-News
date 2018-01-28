package com.mayhsupaing.news.network;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.mayhsupaing.news.MMNewsApp;
import com.mayhsupaing.news.events.LoadedNewsEvent;
import com.mayhsupaing.news.network.responses.GetNewsResponse;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class OKHttpDataAgent implements NewsDataAgent {

    private static OKHttpDataAgent sObjInstance;

    private OKHttpDataAgent() {

    }

    public static OKHttpDataAgent getsObjInstance() {
        if (sObjInstance == null) {
            sObjInstance = new OKHttpDataAgent();
        }
        return sObjInstance;
    }

    @Override
    public void loadNews() {
        new LoadNewsTask().execute("http://padcmyanmar.com/padc-3/mm-news/apis/v1/getMMNews.php");
    }

    @Override
    public void loginUser(Context context, String phoneNo, String password) {

    }

    @Override
    public void registerUser(String name, String password, String phoneNo) {

    }

    private static class LoadNewsTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String url = urls[0];

            OkHttpClient httpClient = new OkHttpClient.Builder() //1
                    .connectTimeout(15, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .readTimeout(60, TimeUnit.SECONDS)
                    .build();

            RequestBody formBody = new FormBody.Builder() //2
                    .add("access_token", "b002c7e1a528b7cb460933fc2875e916")
                    .add("page", "1")
                    .build();

            Request request = new Request.Builder() //3
                    .url(url)
                    .post(formBody)
                    .build();

            String responseString=null;
            try {
                Response response = httpClient.newCall(request).execute(); //4
                if (response.isSuccessful() && response.body() != null) {
                    responseString = response.body().string();
                }

            } catch (Exception e) {
                Log.e(MMNewsApp.LOG_TAG, e.getMessage());
            }

            return responseString;
        }

        @Override
        protected void onPostExecute(String response) // cuz return responseString.
        {
            super.onPostExecute(response);

            Gson gson=new Gson();
            GetNewsResponse getNewsResponse=gson. fromJson(response, GetNewsResponse.class);

            LoadedNewsEvent event=new LoadedNewsEvent(getNewsResponse.getMmNews());
            EventBus eventBus=EventBus.getDefault();
            eventBus.post(event);
        }
    }
}
