package com.example.prefecturailb;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //Hilo para que la actividad inicie y se cierre solita
        //Nota No olvidar modificar el Android Mainfest para que se ejecute primero esta activaidad.
        //mover <intent-filter> del la MainActivity a la de SplashActivity.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this ,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
