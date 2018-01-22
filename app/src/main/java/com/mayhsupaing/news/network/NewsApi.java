package com.mayhsupaing.news.network;

import com.mayhsupaing.news.network.responses.GetLogInResponse;
import com.mayhsupaing.news.network.responses.GetNewsResponse;
import com.mayhsupaing.news.network.responses.GetRegisterResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by Lenovo on 1/6/2018.
 */

public interface NewsApi {

    @FormUrlEncoded
    @POST("getMMNews.php")
    Call<GetNewsResponse> getNews(@Field("page") int page,
                                  @Field("access_token") String accessToken);

    @FormUrlEncoded
    @POST("login.php")
    Call<GetLogInResponse> loginUser(@Field("phoneNo") String phoneNo,
                                        @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<GetRegisterResponse> registerUser(@Field("phoneNo") String phoneNo,
                                        @Field("password") String password,
                                        @Field("name") String name);
}
