package com.mayhsupaing.news.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.adapters.FavouriteAdapter;
import com.mayhsupaing.news.data.vo.FavouriteVO;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 2/4/2018.
 */

public class LikeUsersDialog extends Dialog {

    @BindView(R.id.rv_like_user)
    RecyclerView rvLikeUser;


    private FavouriteAdapter mFavouriteAdapter;


    public LikeUsersDialog(@NonNull Context context, List<FavouriteVO> likeUserList) {
        super(context);
        setContentView(R.layout.dialog_likes_users);
        ButterKnife.bind(this, this);

        setCancelable(false);

        mFavouriteAdapter = new FavouriteAdapter();
        rvLikeUser.setLayoutManager(new LinearLayoutManager(getContext()));
        rvLikeUser.setAdapter(mFavouriteAdapter);

        mFavouriteAdapter.setData(likeUserList);
    }

    @OnClick(R.id.iv_close_dialog)
    public void onTapCloseDialog(View view) {
        dismiss();
    }


}
