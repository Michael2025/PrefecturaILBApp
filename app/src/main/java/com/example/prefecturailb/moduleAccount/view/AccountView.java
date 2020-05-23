package com.example.prefecturailb.moduleAccount.view;

import com.example.prefecturailb.common.pojo.User;

public interface AccountView {

    void openScan();
    void checkPermissionsToApp();
    void onOpenLogin();
    void onError(int message);
}
