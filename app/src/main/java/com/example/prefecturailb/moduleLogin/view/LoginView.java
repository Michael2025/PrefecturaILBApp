package com.example.prefecturailb.moduleLogin.view;

public interface LoginView {
    void showProgress();
    void hideProgress();

    void disableUIComponents();

    void openAccountActivity();

    void onShowError(int resMsg);
}
