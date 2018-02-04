package com.mayhsupaing.news.data.vo;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lenovo on 12/17/2017.
 */

public class NewsVO {
    @SerializedName("news-id")
    private String newsId;
    private String brief;
    private String details;
    private List<String> images;

    @SerializedName("posted-date")
    private String postedDate;

    private PublicationVO publication;

    @SerializedName("favorites")
    private List<FavouriteVO> favourites;

    @SerializedName("comments")
    private List<CommentVO> comment;

    @SerializedName("sent-tos")
    private List<SentToVO> sentTo;


    public String getNewsId() {
        return newsId;
    }

    public String getBrief() {
        return brief;
    }

    public String getDetails() {
        return details;
    }


    public String getPostedDate() {
        return postedDate;
    }

    public PublicationVO getPublication() {
        return publication;
    }

    public List<String> getImages() {
        return images;
    }

    public List<FavouriteVO> getFavourites() {
        if(favourites==null){
            favourites=new ArrayList<>();
        }
        return favourites;
    }

    public List<CommentVO> getComment() {
        //null safety logic
        if(comment==null){
            comment=new ArrayList<>();
        }
        return comment;
    }

    public List<SentToVO> getSentTo() {
        if(sentTo==null){
            sentTo=new ArrayList<>();
        }
        return sentTo;
    }
}
