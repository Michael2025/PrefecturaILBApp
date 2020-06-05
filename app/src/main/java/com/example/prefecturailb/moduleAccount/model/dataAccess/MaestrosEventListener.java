package com.example.prefecturailb.moduleAccount.model.dataAccess;

import com.example.prefecturailb.common.pojo.Maestro;

import java.util.ArrayList;

public interface MaestrosEventListener {
    void onDataChange(ArrayList<Maestro> maestros, int typeEvent);
    void onChildError(int resMsg, int typeEvent);
}
