package com.mayhsupaing.news.delegates;

import com.mayhsupaing.news.data.vo.NewsVO;

/**
 * Created by Lenovo on 12/17/2017.
 */

public interface NewsActionDelegate {
    void onTapNewsItem(NewsVO tappedNews);
    void onTapCommentButton();
    void onTapSendToButton(NewsVO tappedNews);
    void onTapFavouriteButton();

    void onTapLikeUser(NewsVO tappedNews);
    void onTapCommentUser(NewsVO tappedNews);
    void onTapSendToUser(NewsVO tappedNews);
}
