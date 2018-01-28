package com.mayhsupaing.news.network;

import android.content.Context;

/**
 * Created by Lenovo on 12/23/2017.
 */

public interface NewsDataAgent {

    /**
     * load news form network api.
     */
    void loadNews();

    /**
     * Login user;
     * @param context
     * @param phoneNo
     * @param password
     */
    void loginUser(Context context, String phoneNo, String password);

    void registerUser(String name,String password,String phoneNo);
}
