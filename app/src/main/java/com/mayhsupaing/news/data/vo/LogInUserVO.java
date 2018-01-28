package com.mayhsupaing.news.data.vo;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class LogInUserVO {

    private int userId;
    private String name;
    private String email;
    private String phoneNo;
    private String profileUrl;
    private String coverUrl;

    public LogInUserVO() {
    }

    //if we create a constructor, there isn't default constructor. So, need to create default.
    public LogInUserVO(int userId, String name, String email,
                       String phoneNo, String profileUrl, String coverUrl) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.profileUrl = profileUrl;
        this.coverUrl = coverUrl;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public String getCoverUrl() {
        return coverUrl;
    }
}
