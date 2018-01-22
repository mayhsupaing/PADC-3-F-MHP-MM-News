package com.mayhsupaing.news.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.mayhsupaing.news.R;
import com.mayhsupaing.news.data.models.RegisterUserModel;
import com.mayhsupaing.news.data.vo.RegisterUserVO;
import com.mayhsupaing.news.events.SuccessLoginEvent;
import com.mayhsupaing.news.events.SuccessRegisterEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Lenovo on 1/20/2018.
 */

public class RegisterFragment extends Fragment {

    @BindView(R.id.et_name)
    EditText etName;

    @BindView(R.id.et_password)
    EditText etPassword;

    @BindView(R.id.et_phone)
    EditText etPhoneNo;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_register,container,false);
        ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.btn_register)
    public void onTapRegister(View view){
        String name=etName.getText().toString();
        String password=etPassword.getText().toString();
        String phoneNo=etPhoneNo.getText().toString();
        RegisterUserModel.getsObjInstance().registerUser(name,password,phoneNo);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRegisterUserSuccess(SuccessRegisterEvent event){
        if(getActivity()!=null){
            getActivity().onBackPressed(); //Go Back to navigation view
        }
    }
}
