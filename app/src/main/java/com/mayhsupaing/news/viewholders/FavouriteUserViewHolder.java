package com.mayhsupaing.news.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.vo.FavouriteVO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 2/4/2018.
 */

public class FavouriteUserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    @BindView(R.id.tv_name)
    TextView tvName;

    @BindView(R.id.tv_phoneNo)
    TextView tvPhoneNo;

    @BindView(R.id.tv_time_stamp)
    TextView tvTimeStamp;


    public FavouriteUserViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void setData(FavouriteVO favourite){
        Glide.with(ivUserProfile.getContext())
                .load(favourite.getActedUser().getProfileImage())
                .into(ivUserProfile);

        tvName.setText(favourite.getActedUser().getUserName());
        String originalTimeFormat=favourite.getFavouriteDate();

        SimpleDateFormat sdfOriginalFormat=new SimpleDateFormat("E MMM dd hh:mm:ss Z yyyy");
        try {
            Date date=sdfOriginalFormat.parse(originalTimeFormat);
            SimpleDateFormat sdfPresentableFormat=new SimpleDateFormat("hh:mm a',' MMM dd yyyy");
            String presentableTimeFormat=sdfPresentableFormat.format(date);
            tvTimeStamp.setText(presentableTimeFormat);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
