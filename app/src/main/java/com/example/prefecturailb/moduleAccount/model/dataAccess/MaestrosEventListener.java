package com.example.prefecturailb.moduleAccount.model.dataAccess;

import com.google.firebase.database.DataSnapshot;

public interface MaestrosEventListener {

    void onChildAdd(String name, int typeEvent);
    void onChildChange(String name, int typeEvent);
    void onChildRemove(String name, int typeEvent);
    void onChildError(int resMsg, int typeEvent);
}
