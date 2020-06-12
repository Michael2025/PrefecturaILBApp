package com.example.prefecturailb.moduleAccount;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleAccount.events.AccountEvents;


public interface AccountPresenter {

    void onCreate();
    void onPause();
    void onResume();
    void onDestroy();
    void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults);
    void onResult(int requestCode, int resultCode, @Nullable Intent data);
    void signOut();
    void openScan();
    void getUserInfo();
    void onEvent(AccountEvents events);


}
