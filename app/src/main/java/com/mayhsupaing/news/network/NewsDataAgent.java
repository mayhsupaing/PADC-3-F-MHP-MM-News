package com.mayhsupaing.news.network;

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
     * @param phoneNo
     * @param password
     */
    void loginUser(String phoneNo,String password);

    void registerUser(String name,String password,String phoneNo);
}
