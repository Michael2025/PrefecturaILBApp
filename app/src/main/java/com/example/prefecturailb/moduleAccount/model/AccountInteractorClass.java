package com.example.prefecturailb.moduleAccount.model;

import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleAccount.model.dataAccess.Authentication;
import com.example.prefecturailb.moduleAccount.model.dataAccess.MaestrosEventListener;
import com.example.prefecturailb.moduleAccount.model.dataAccess.RealTimeDataBase;

import org.greenrobot.eventbus.EventBus;

public class AccountInteractorClass implements AccountInteractor{

    Authentication mauthentication;
    RealTimeDataBase mrealTimeDataBase;

    public AccountInteractorClass (){
        mauthentication = new Authentication();
        mrealTimeDataBase = new RealTimeDataBase();
    }

    private void post(String name, int typeEvent, int resMsg){
        AccountEvents events = new AccountEvents();
        events.setMessage(resMsg);
        events.setTypeEvent(typeEvent);
        events.setName(name);
        EventBus.getDefault().post(events);
    }

    private void post(String name, int typeEvent){
        post(name, typeEvent, 0);
    }

    private void post(int typeEvent, int resMsg){
        post("", typeEvent, resMsg);
    }

    @Override
    public void signOut() {
        mauthentication.signOut();
    }

    @Override
    public void onSubscribeToMaestros() {
        mrealTimeDataBase.onSubscribeToMaestros(new MaestrosEventListener() {
            @Override
            public void onChildAdd(String name, int typeEvent) {
                post(name, typeEvent);
            }

            @Override
            public void onChildChange(String name, int typeEvent) {
                post(name, typeEvent);
            }

            @Override
            public void onChildRemove(String name, int typeEvent) {
                post(name, typeEvent);
            }

            @Override
            public void onChildError(int resMsg, int typeEvent) {
                post(resMsg, typeEvent);
            }
        });
        }

    @Override
    public void onUnsubscribeToMaestros() {
        mrealTimeDataBase.onUnsubscribeMaestros();
    }
}
