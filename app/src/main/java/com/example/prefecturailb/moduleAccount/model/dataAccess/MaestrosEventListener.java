package com.example.prefecturailb.moduleAccount.model.dataAccess;

import com.example.prefecturailb.common.pojo.Maestro;

import java.util.ArrayList;

public interface MaestrosEventListener {

    void onChildAdd(Maestro maestro, int typeEvent);
    void onChildChange(Maestro maestro, int typeEvent);
    void onChildRemove(Maestro maestro, int typeEvent);
    void onChildError(int resMsg, int typeEvent);
}
