package com.mayhsupaing.news.data.vo;

/**
 * Created by Lenovo on 12/17/2017.
 */

public class FavouriteVO {
    private String favouriteId;
    private String favouriteDate;
    private ActedUserVO actedUser;

    public String getFavouriteId() {
        return favouriteId;
    }

    public String getFavouriteDate() {
        return favouriteDate;
    }

    public ActedUserVO getActedUser() {
        return actedUser;
    }
}
