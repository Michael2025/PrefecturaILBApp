package com.example.prefecturailb.moduleAccount;

import androidx.annotation.NonNull;

import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleAccount.model.AccountInteractor;
import com.example.prefecturailb.moduleAccount.model.AccountInteractorClass;
import com.example.prefecturailb.moduleAccount.view.AccountView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

public class AccountPresenterClass implements AccountPresenter{

    private AccountView mView;
    private AccountInteractor mInteractor;

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

    @Subscribe
    @Override
    public void onEvent(AccountEvents events) {
        switch (events.getTypeEvent()){
            case AccountEvents.ADD_SUCCEFULL:
            case AccountEvents.CHANGE_SUCCEFULL:
            case AccountEvents.REMOVE_SUCCEFULL:
                mView.onGetList(events.getMaestro());
                break;
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
}
