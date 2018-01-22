package viewpods;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mayhsupaing.news.R;
import com.mayhsupaing.news.activities.NewsByCategoryActivity;
import com.mayhsupaing.news.data.vo.LogInUserVO;
import com.mayhsupaing.news.delegates.LogInUserDelegate;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/14/2018.
 */

public class LogInUserViewPod extends RelativeLayout {

    @BindView(R.id.iv_bg)
    ImageView ivBackground;

    @BindView(R.id.iv_user_profile)
    ImageView ivUserProfile;

    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;

    @BindView(R.id.tv_name)
    TextView tvName;

    private LogInUserDelegate mDelegate;

    public LogInUserViewPod(Context context) {
        super(context);
    }

    public LogInUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LogInUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }


    public void bindData(LogInUserVO loginUser){
        Glide.with(ivBackground.getContext())
                .load(loginUser.getCoverUrl())
                .into(ivBackground);

        Glide.with(ivUserProfile.getContext())
                .load(loginUser.getProfileUrl())
                .into(ivUserProfile);

        tvUserPhone.setText(loginUser.getPhoneNo());
        tvName.setText(loginUser.getName());
    }

    //setter method.
    public void setDelegate(LogInUserDelegate logInUserDelegate){
        mDelegate=logInUserDelegate;
    }

    @OnClick(R.id.btn_log_out)
    public void onTapLogOut(View view){
        mDelegate.onTapLogOut();
    }


}
