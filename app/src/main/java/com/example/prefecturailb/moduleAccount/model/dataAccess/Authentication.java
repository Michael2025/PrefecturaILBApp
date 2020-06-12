package com.example.prefecturailb.moduleAccount.model.dataAccess;

import com.example.prefecturailb.common.model.dataAccess.FirebaseAuthenticationAPI;
import com.example.prefecturailb.common.pojo.User;

public class Authentication {

    FirebaseAuthenticationAPI mAuthenticationAPI;

    public Authentication (){
        mAuthenticationAPI = FirebaseAuthenticationAPI.getInstance();
    }

    public void signOut(){
        mAuthenticationAPI.getmFirebaseAuth().signOut();
    }

    public String getUserEmail(){
        User user = new User();
        user.setEmail(mAuthenticationAPI.getAuthUser().getEmail());
        return user.getEmail();
    }

}
