package com.example.prefecturailb.common.model.dataAccess;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRealtimeDatabaseAPI {
    public static final String PATH_USER="user";

    private DatabaseReference mDatabaseReference;
    private static class SingletonHolder{
        private static final FirebaseRealtimeDatabaseAPI INSTANCE = new FirebaseRealtimeDatabaseAPI();
    }

    public static FirebaseRealtimeDatabaseAPI getInstance(){
        return SingletonHolder.INSTANCE;
    }
    private FirebaseRealtimeDatabaseAPI (){
        this.mDatabaseReference= FirebaseDatabase.getInstance().getReference();
    }

    /*
    * References
    * */
    public DatabaseReference getRootReference(){
        return mDatabaseReference.getRoot();
    }

    public DatabaseReference getUserReferenceUid(String uid){
        return  getRootReference().child(PATH_USER).child(uid);
    }

}
