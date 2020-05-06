package com.example.prefecturailb.moduleLogin;

import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleLogin.events.LoginEvents;
import com.example.prefecturailb.moduleLogin.model.LoginInteractor;
import com.example.prefecturailb.moduleLogin.model.LoginInteractorClass;
import com.example.prefecturailb.moduleLogin.view.LoginView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class LoginPresenterClass implements LoginPresenter {
    private LoginView mView;
    private LoginInteractor mInteractor;

    public LoginPresenterClass(LoginView mView) {
        this.mView = mView;
        this.mInteractor=new LoginInteractorClass();
    }

    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        mInteractor.onPause();
    }

    @Override
    public void onResume() {
        if (mView!=null){
            mInteractor.onResume();
        }
    }

    @Override
    public void onDestroy() {
        mView=null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void getStatusAuth() {
    if (setProgress()){
        mInteractor.getStatusAuth();
    }
    }

    private boolean setProgress() {
        if (mView!=null){
            mView.showProgress();
            return true;
        }
        return false;
    }

    @Override
    public void signIn(User user) {
        if (mView!=null){
                mView.disableUIComponents();
                mInteractor.signIn(user);
        }
    }

    @Subscribe
    @Override
    public void onEventListener(LoginEvents events) {
        if (mView!=null){
            mView.hideProgress();
            switch (events.getType()){
                case LoginEvents.LOGIN_AUTH_STATUS_SUCCESS:
                case LoginEvents.LOGIN_SUCCESS:
                    mView.openAccountActivity();
                    break;
                case LoginEvents.LOGIN_NETWORK_ERROR:
                case LoginEvents.LOGIN_CREDENTIAL_ERROR:
                case LoginEvents.LOGIN_EMAIL_ERROR:
                case LoginEvents.LOGIN_USER_ERROR:
                case LoginEvents.LOGIN_UNKNOWN:
                    mView.onShowError(events.getResMsg());
                    break;
            }
        }
    }
}
