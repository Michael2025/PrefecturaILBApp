package com.example.prefecturailb.moduleLogin.model;

import com.example.prefecturailb.common.BasicListener;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleLogin.events.LoginEvents;
import com.google.firebase.auth.FirebaseUser;

import org.greenrobot.eventbus.EventBus;

public class LoginInteractorClass implements LoginInteractor{
    private Authentication mAuthentication;

    public LoginInteractorClass() {
        mAuthentication= new Authentication();
    }

    @Override
    public void onResume() {
        mAuthentication.onResume();
    }

    @Override
    public void onPause() {
        mAuthentication.onPause();
    }

    @Override
    public void getStatusAuth() {
        mAuthentication.getStatusAuth(new StatusAuthCallback() {
            @Override
            public void onGetUser(FirebaseUser user) {
                post(LoginEvents.LOGIN_AUTH_STATUS_SUCCESS,user);
            }
        });
    }

    @Override
    public void signIn(User user) {
        mAuthentication.logIn(user, new BasicListener() {
            @Override
            public void onSuccess(int event) {
                post(event);
            }

            @Override
            public void onError(int typeEvent, int resMsg) {
                post(typeEvent,resMsg);
            }
        });
    }

    private void post(int event){
        post(event,0,null);
    }

    private void post(int typeEvent, int resMsg){
        post(typeEvent,resMsg,null);
    }
    private void post(int typeEvent, FirebaseUser user){
        post(typeEvent,0,user);
    }
    private void post(int typeEvent, int resMsg, FirebaseUser user){
        LoginEvents event = new LoginEvents();
        event.setType(typeEvent);
        event.setResMsg(resMsg);
        event.setUser(user);
        EventBus.getDefault().post(event);
    }

}
