package com.example.prefecturailb.moduleLogin.model;

import com.example.prefecturailb.common.pojo.User;

public interface LoginInteractor {
    void onResume();
    void onPause();

    void getStatusAuth();
    void signIn(User user);


}
