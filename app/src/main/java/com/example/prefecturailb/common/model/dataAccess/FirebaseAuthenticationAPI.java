package com.example.prefecturailb.common.model.dataAccess;

import com.example.prefecturailb.common.pojo.User;
import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthenticationAPI {
    private FirebaseAuth mFirebaseAuth;

    private static class SingletonHolder{
        private static final FirebaseAuthenticationAPI INSTANCE= new FirebaseAuthenticationAPI();
    }

    public static FirebaseAuthenticationAPI getInstance(){
        return  SingletonHolder.INSTANCE;
    }

    private  FirebaseAuthenticationAPI(){
        this.mFirebaseAuth= FirebaseAuth.getInstance();
    }

    public FirebaseAuth getmFirebaseAuth(){
        return  this.mFirebaseAuth;
    }

    public User getAuthUser(){
        User user= new User();
        if (mFirebaseAuth != null && mFirebaseAuth.getCurrentUser() != null){
            user.setUid(mFirebaseAuth.getCurrentUser().getUid());
            user.setEmail(mFirebaseAuth.getCurrentUser().getEmail());
        }
        return user;
    }
}
