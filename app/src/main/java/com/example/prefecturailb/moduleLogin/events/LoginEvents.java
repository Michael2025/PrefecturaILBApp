package com.example.prefecturailb.moduleLogin.events;

import com.google.firebase.auth.FirebaseUser;

public class LoginEvents {
    public static final int LOGIN_SUCCESS=0;
    public static final int LOGIN_AUTH_STATUS_SUCCESS=1;

    public static final int LOGIN_NETWORK_ERROR=100;
    public static final int LOGIN_EMAIL_ERROR=101;
    public static final int LOGIN_CREDENTIAL_ERROR=102;
    public static final int LOGIN_USER_ERROR=103;
    public static final int LOGIN_UNKNOWN=104;

    private FirebaseUser user;

    private int type;
    private int resMsg;

    public LoginEvents() {
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getResMsg() {
        return resMsg;
    }

    public void setResMsg(int resMsg) {
        this.resMsg = resMsg;
    }

    public FirebaseUser getUser() {
        return user;
    }

    public void setUser(FirebaseUser user) {
        this.user = user;
    }
}
