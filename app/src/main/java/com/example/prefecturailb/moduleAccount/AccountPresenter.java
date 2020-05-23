package com.example.prefecturailb.moduleAccount;

import androidx.annotation.NonNull;

import com.example.prefecturailb.moduleAccount.events.AccountEvents;


public interface AccountPresenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    void signOut();
    void openScan();

    void onEvent(AccountEvents events);


}
