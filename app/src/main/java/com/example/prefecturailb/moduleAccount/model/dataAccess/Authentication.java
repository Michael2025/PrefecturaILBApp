package com.example.prefecturailb.moduleAccount.model.dataAccess;

import com.example.prefecturailb.common.BasicListener;
import com.example.prefecturailb.common.model.dataAccess.FirebaseAuthenticationAPI;

public class Authentication {

    FirebaseAuthenticationAPI mAuthenticationAPI;

    public Authentication (){
        mAuthenticationAPI = FirebaseAuthenticationAPI.getInstance();
    }

    public void signOut(){
        mAuthenticationAPI.getmFirebaseAuth().signOut();
    }

}
