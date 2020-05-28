package com.example.prefecturailb.moduleAccount.view;

import com.example.prefecturailb.common.pojo.Maestro;

import java.util.ArrayList;

public interface AccountView {

    void openScan();
    void checkPermissionsToApp();
    void onOpenLogin();
    void onError(int message);
    void onGetList(ArrayList<Maestro> maestros);
}
