package com.example.prefecturailb.moduleAccount.model.dataAccess;

import androidx.annotation.NonNull;

import com.example.prefecturailb.R;
import com.example.prefecturailb.common.model.dataAccess.FirebaseRealtimeDatabaseAPI;
import com.example.prefecturailb.common.pojo.Maestro;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleAccount.events.AccountEvents;
import com.example.prefecturailb.moduleLogin.events.LoginEvents;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RealTimeDataBase {

    //Nombre del profesor
    private ValueEventListener valueEventListener;

    FirebaseRealtimeDatabaseAPI mDatabaseAPI;

    public RealTimeDataBase(){
        mDatabaseAPI = FirebaseRealtimeDatabaseAPI.getInstance();
    }

    private ArrayList<Maestro> getMaestroName(DataSnapshot dataSnapshot){
        ArrayList <Maestro> maestros = new ArrayList<Maestro>();
           for (DataSnapshot snapshot: dataSnapshot.getChildren()){
               Maestro maestro = new Maestro();
               if (snapshot.getKey()!=null){
                   maestro.setNombre(snapshot.getKey());
                   maestros.add(maestro);
               }
           }

        return maestros;
    }

    public void onSubscribeToMaestros(MaestrosEventListener listener){
    if (valueEventListener==null){
        valueEventListener= new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listener.onDataChange(getMaestroName(dataSnapshot),AccountEvents.ADD_SUCCEFULL);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onChildError(R.string.error_internet,AccountEvents.CONNECTION_ERROR);
            }
        };
    }
    mDatabaseAPI.getMaestroReference().addValueEventListener(valueEventListener);

    }

    public void onUnsubscribeMaestros(){
        if(valueEventListener != null){
            mDatabaseAPI.getMaestroReference().removeEventListener(valueEventListener);
        }
    }

    public void getUserByEmail(String email, UserCallBack userCallBack){
        DatabaseReference databaseReference = mDatabaseAPI.getUsuariosReference();
        Query userByEmail = databaseReference.orderByChild(User.EMAIL).equalTo(email);
        userByEmail.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot: dataSnapshot.getChildren() ){
                    User user = snapshot.getValue(User.class);
                    if (user != null) {
                        user.setUid(dataSnapshot.getKey());
                        userCallBack.getUserByEmail(AccountEvents.GET_USER_SUCCESFULL,user);
                    }
                }


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if (databaseError.getCode() == DatabaseError.NETWORK_ERROR) {
                    userCallBack.onError(LoginEvents.GET_USER_NETWORK_ERROR, R.string.error_internet);
                } else {
                    userCallBack.onError(LoginEvents.LOGIN_UNKNOWN, R.string.error_unknown);
                }
            }
        });
    }

}
