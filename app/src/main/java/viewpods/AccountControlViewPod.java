package viewpods;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.models.LogInUserModel;
import com.mayhsupaing.news.data.models.RegisterUserModel;
import com.mayhsupaing.news.delegates.BeforeLoginDelegate;
import com.mayhsupaing.news.delegates.LogInUserDelegate;
import com.mayhsupaing.news.delegates.RegisterUserDelegate;
import com.mayhsupaing.news.events.SuccessLoginEvent;
import com.mayhsupaing.news.events.SuccessRegisterEvent;
import com.mayhsupaing.news.events.UserLogOutEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Lenovo on 1/21/2018.
 */

public class AccountControlViewPod extends FrameLayout {

    @BindView(R.id.vp_before_login)
    BeforeLogInUserViewPod vpBeforeLogin;

    @BindView(R.id.vp_login_user)
    LogInUserViewPod vpLoginUser;

    @BindView(R.id.vp_register_user)
    RegisterUserViewPod vpRegisterUser;

    public AccountControlViewPod(@NonNull Context context) {
        super(context);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public AccountControlViewPod(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this, this);

        refreshUserSession();
        refreshRegisterUserSession();

        EventBus.getDefault().register(this);
    }

    //because b.l.d involve in  accountControl
    public void setDelegate(BeforeLoginDelegate beforeLoginDelegate) {
        vpBeforeLogin.setDelegate(beforeLoginDelegate);
    }

    public void setDelegate(LogInUserDelegate logInUserDelegate) {
        vpLoginUser.setDelegate(logInUserDelegate);
    }

    public void setDelegate(RegisterUserDelegate registerUserDelegate){
        vpRegisterUser.setDelegate(registerUserDelegate);
    }

    /**
     * Log In
     */

    public void refreshUserSession() {
        if (LogInUserModel.getsObjInstance().isUserLogIn()) {
            vpBeforeLogin.setVisibility(View.GONE);
            vpLoginUser.setVisibility(View.VISIBLE);
        } else {
            vpBeforeLogin.setVisibility(View.VISIBLE);
            vpLoginUser.setVisibility(View.GONE);
        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onLoginUserSuccess(SuccessLoginEvent event) {
        vpBeforeLogin.setVisibility(View.GONE);
        vpLoginUser.setVisibility(View.VISIBLE);

        vpLoginUser.bindData(event.getLoginUserList()); //bind data
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //UI comp changes
    public void onLogOutUser(UserLogOutEvent event) {
        vpBeforeLogin.setVisibility(View.VISIBLE);
        vpLoginUser.setVisibility(View.GONE);
    }


    /**
     * Register.
     */

    public void refreshRegisterUserSession() {
        if (RegisterUserModel.getsObjInstance().isUserRegister()) {
            vpBeforeLogin.setVisibility(View.GONE);
            vpRegisterUser.setVisibility(View.VISIBLE);
        } else {
            vpBeforeLogin.setVisibility(View.VISIBLE);
            vpRegisterUser.setVisibility(View.GONE);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterUserSuccess(SuccessRegisterEvent event) {
        vpBeforeLogin.setVisibility(View.GONE);
        vpRegisterUser.setVisibility(View.VISIBLE);

        vpRegisterUser.bindData(event.getRegisterUserList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //UI comp changes
    public void onLogOutRegisterUser(UserLogOutEvent event) {
        vpBeforeLogin.setVisibility(View.VISIBLE);
        vpRegisterUser.setVisibility(View.GONE);
    }

}
