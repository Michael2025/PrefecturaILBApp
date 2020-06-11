package com.example.prefecturailb.moduleAccount;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.prefecturailb.R;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleAccount.model.AccountInteractor;
import com.example.prefecturailb.moduleAccount.model.AccountInteractorClass;
import com.example.prefecturailb.moduleAccount.view.AccountView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AccountPresenterClass implements AccountPresenter{

    private AccountView mView;
    private AccountInteractor mInteractor;
    private User user;

    public AccountPresenterClass (AccountView mView){
        this.mView = mView;
        this.mInteractor = new AccountInteractorClass();
    }


    @Override
    public void onCreate() {
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        if(mView != null){
            mInteractor.onUnsubscribeToMaestros();
        }
    }

    @Override
    public void onResume() {
        if (mView != null){
            mInteractor.onSubscribeToMaestros();
        }
    }

    @Override
    public void onDestroy() {
        mView = null;
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

    }

    @Override
    public void onResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if (result!= null){
            String QR= result.getContents();
            if (QR == null){
                mView.onError(R.string.error_canceled);
            }else {
                try {
                    String [] QrContent=QR.split(";");
                    Log.e("QR",QrContent[0]+"  "+QrContent[1]);
                } catch (Exception e) {
                    Log.e("QR",e.getMessage());
                }
            }
        }
    }

    @Subscribe
    @Override
    public void onEvent(AccountEvents events) {
        switch (events.getTypeEvent()){
            case AccountEvents.ADD_SUCCEFULL:
                mView.onGetList(events.getMaestro());
                break;
            case AccountEvents.GET_USER_SUCCESFULL:
                user=events.getUser();
                break;
            case AccountEvents.GET_USER_NETWORK_ERROR:
            case AccountEvents.CONNECTION_ERROR:
            case AccountEvents.SEARCH_ERROR:
            case AccountEvents.UNKOWN_ERROR:
                mView.onError(events.getMessage());
                break;
        }
    }

    @Override
    public void signOut() {
        mInteractor.signOut();
        mView.onOpenLogin();
    }

    @Override
    public void openScan() {
        mView.checkPermissionsToApp();
        mView.openScan();
    }

    @Override
    public void getUserInfo() {
        mInteractor.getUserInfo();
    }

    @Override
    public User getInfo() {
        return user;
    }
}
