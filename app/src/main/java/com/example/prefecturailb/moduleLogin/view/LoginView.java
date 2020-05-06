package com.example.prefecturailb.moduleLogin.view;

public interface LoginView {
    void showProgress();
    void hideProgress();

    void disableUIComponents();
    void enableUIComponents();
    void openAccountActivity();

    void onShowError(int resMsg);
}
