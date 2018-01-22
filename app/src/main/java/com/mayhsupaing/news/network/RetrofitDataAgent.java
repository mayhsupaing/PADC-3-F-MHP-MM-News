package com.mayhsupaing.news.network;

import com.google.gson.Gson;
import com.mayhsupaing.news.events.LoadedNewsEvent;
import com.mayhsupaing.news.events.SuccessLoginEvent;
import com.mayhsupaing.news.events.SuccessRegisterEvent;
import com.mayhsupaing.news.network.responses.GetLogInResponse;
import com.mayhsupaing.news.network.responses.GetNewsResponse;
import com.mayhsupaing.news.network.responses.GetRegisterResponse;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 1/6/2018.
 */

public class RetrofitDataAgent implements NewsDataAgent {

    private static RetrofitDataAgent sObjInstance;

    private NewsApi newsApi;

    private RetrofitDataAgent(){

        OkHttpClient httpClient = new OkHttpClient.Builder() //1
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder() //2
                .baseUrl("http://padcmyanmar.com/padc-3/mm-news/apis/v1/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(httpClient)
                .build();

        newsApi=retrofit.create(NewsApi.class);

    }

    public static RetrofitDataAgent getsObjInstance(){
        if(sObjInstance==null){
            sObjInstance=new RetrofitDataAgent();
        }
        return sObjInstance;
    }
    @Override
    public void loadNews() {


        Call<GetNewsResponse> getNewsResponseCall=newsApi.getNews(1,"b002c7e1a528b7cb460933fc2875e916");

        getNewsResponseCall.enqueue(new Callback<GetNewsResponse>() {
            @Override
            public void onResponse(Call<GetNewsResponse> call, Response<GetNewsResponse> response) {
                GetNewsResponse getNewsResponse=response.body();
                if(getNewsResponse!=null) {
                    LoadedNewsEvent event = new LoadedNewsEvent(getNewsResponse.getMmNews());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetNewsResponse> call, Throwable t) {


            }
        });

    }

    @Override
    public void loginUser(String phoneNo, String password) {

        Call<GetLogInResponse> getLogInResponseCall= newsApi.loginUser(phoneNo,password);

        getLogInResponseCall.enqueue(new Callback<GetLogInResponse>() {

            @Override
            public void onResponse(Call<GetLogInResponse> call, Response<GetLogInResponse> response) {
                GetLogInResponse getLogInResponse=response.body();
                if(getLogInResponse!=null) {
                    SuccessLoginEvent event = new SuccessLoginEvent(getLogInResponse.getLoginUser());
                    EventBus.getDefault().post(event);
                }
            }

            @Override
            public void onFailure(Call<GetLogInResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void registerUser(String name, String password, String phoneNo) {
        Call<GetRegisterResponse> getRegisterResponseCall= newsApi.registerUser(phoneNo,password,name);

       getRegisterResponseCall.enqueue(new Callback<GetRegisterResponse>() {

           @Override
           public void onResponse(Call<GetRegisterResponse> call, Response<GetRegisterResponse> response) {
               GetRegisterResponse getRegisterResponse=response.body();
               if(getRegisterResponse!=null) {
                   SuccessRegisterEvent event = new SuccessRegisterEvent(getRegisterResponse.getLoginUser());
                   EventBus.getDefault().post(event);
               }
           }

           @Override
           public void onFailure(Call<GetRegisterResponse> call, Throwable t) {

           }
       });
    }
}
