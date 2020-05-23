package com.example.prefecturailb.moduleAccount.events;

public class AccountEvents {

    public static final int SEARCH_SUCCEFULL = 0;
    public static final int ADD_SUCCEFULL = 1;
    public static final int CHANGE_SUCCEFULL = 2;
    public static final int REMOVE_SUCCEFULL = 3;
    public static final int CONNECTION_ERROR = 100;
    public static final int SEARCH_ERROR = 102;
    public static final int UNKOWN_ERROR = 103;

    private int typeEvent;
    private int message;
    private String name;

    public AccountEvents(){

    }

    public int getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(int typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getMessage() {
        return message;
    }

    public void setMessage(int message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
