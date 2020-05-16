package com.example.prefecturailb.moduleLogin;


import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleLogin.events.LoginEvents;

public interface LoginPresenter {
    // Ciclo de vida de una Activity en android
    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    //User exist
    void getStatusAuth();
    void signIn(User user);

    void onEventListener(LoginEvents events);
}
