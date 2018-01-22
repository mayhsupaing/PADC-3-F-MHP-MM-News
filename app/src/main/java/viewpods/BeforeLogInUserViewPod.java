package viewpods;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.delegates.BeforeLoginDelegate;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class BeforeLogInUserViewPod extends RelativeLayout {



    private BeforeLoginDelegate mDelegate;

    public BeforeLogInUserViewPod(Context context) {
        super(context);
    }

    public BeforeLogInUserViewPod(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BeforeLogInUserViewPod(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this,this);
    }

    public void setDelegate(BeforeLoginDelegate delegate){
        mDelegate=delegate;
    }

    @OnClick(R.id.btn_to_login)
    public void  onTapToLogin(View view){
        mDelegate.onTapToLogin();
    }

    @OnClick(R.id.btn_to_register)
    public void onTapToRegister(View view){
        mDelegate.onTapToRegister();
    }
}
