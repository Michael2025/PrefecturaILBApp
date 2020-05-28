package com.example.prefecturailb.moduleAccount.model.dataAccess;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.solver.widgets.Snapshot;

import com.example.prefecturailb.R;
import com.example.prefecturailb.common.model.dataAccess.FirebaseRealtimeDatabaseAPI;
import com.example.prefecturailb.common.pojo.Maestro;
import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleAccount.model.dataAccess.MaestrosEventListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class RealTimeDataBase {

    //Nombre del profesor

    private ChildEventListener childEventListener;

    FirebaseRealtimeDatabaseAPI mDatabaseAPI;

    public RealTimeDataBase(){
        mDatabaseAPI = FirebaseRealtimeDatabaseAPI.getInstance();
    }

    private Maestro getMaestroName(DataSnapshot dataSnapshot){
        //ArrayList <Maestro> maestros = new ArrayList<Maestro>();

            Maestro maestro = dataSnapshot.getValue(Maestro.class);

        return maestro;
    }

    public void onSubscribeToMaestros(MaestrosEventListener listener){
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    listener.onChildAdd(getMaestroName(dataSnapshot), AccountEvents.ADD_SUCCEFULL);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    listener.onChildChange(getMaestroName(dataSnapshot), AccountEvents.CHANGE_SUCCEFULL);
                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                    listener.onChildRemove(getMaestroName(dataSnapshot), AccountEvents.REMOVE_SUCCEFULL);
                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {
                    switch (databaseError.getCode()){
                        case DatabaseError.NETWORK_ERROR:
                            listener.onChildError(R.string.error_internet, AccountEvents.CONNECTION_ERROR);
                            break;
                        case DatabaseError.UNKNOWN_ERROR:
                            listener.onChildError(R.string.error_unknown, AccountEvents.UNKOWN_ERROR);
                            break;
                    }
                }
            };
            mDatabaseAPI.getMaestroReference().addChildEventListener(childEventListener);
        }
    }

    public void onUnsubscribeMaestros(){
        if(childEventListener != null){
            mDatabaseAPI.getMaestroReference().removeEventListener(childEventListener);
        }
    }

}
