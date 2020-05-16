package com.example.prefecturailb.moduleLogin.model;

import androidx.annotation.NonNull;
import com.example.prefecturailb.R;
import com.example.prefecturailb.common.BasicListener;
import com.example.prefecturailb.common.model.dataAccess.FirebaseAuthenticationAPI;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleLogin.events.LoginEvents;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Authentication {
    private FirebaseAuthenticationAPI mAuthenticationAPI;
    private FirebaseAuth.AuthStateListener mStateListener;

    public Authentication() {
        this.mAuthenticationAPI= FirebaseAuthenticationAPI.getInstance();
    }

    public void onResume(){
        mAuthenticationAPI.getmFirebaseAuth().addAuthStateListener(mStateListener);
    }
    public void onPause(){
         if (mStateListener!=null){
             mAuthenticationAPI.getmFirebaseAuth().removeAuthStateListener(mStateListener);
         }
    }

    public void getStatusAuth(StatusAuthCallback callback){
        mStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user= firebaseAuth.getCurrentUser();
                if (user!=null){
                    callback.onGetUser(user);
                }
            }
        };
    }
    public void logIn(User user, BasicListener listener){
        mAuthenticationAPI.getmFirebaseAuth()
                .signInWithEmailAndPassword(user.getEmail(), user.getPassword())
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        listener.onSuccess(LoginEvents.LOGIN_SUCCESS);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseNetworkException){
                            listener.onError(LoginEvents.LOGIN_NETWORK_ERROR, R.string.error_internet);
                        }else if (e instanceof FirebaseAuthEmailException){
                            listener.onError(LoginEvents.LOGIN_EMAIL_ERROR,R.string.error_email);
                        }else if(e instanceof FirebaseAuthInvalidCredentialsException){
                            listener.onError(LoginEvents.LOGIN_CREDENTIAL_ERROR,R.string.error_invalid_credencial);
                        }else if (e instanceof FirebaseAuthInvalidUserException){
                            listener.onError(LoginEvents.LOGIN_USER_ERROR,R.string.error_user_not_register);
                        }else {
                            listener.onError(LoginEvents.LOGIN_UNKNOWN,R.string.error_unknown);
                        }
                    }
                });
    }
    public User getCurrentUser(){
        return mAuthenticationAPI.getAuthUser();
    }
}
