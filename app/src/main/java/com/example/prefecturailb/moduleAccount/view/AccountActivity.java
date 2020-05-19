package com.example.prefecturailb.moduleAccount.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.prefecturailb.R;
import com.example.prefecturailb.moduleAccount.utils.Constants;
import com.example.prefecturailb.moduleLogin.view.MainActivity;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.zxing.integration.android.IntentIntegrator;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//Franku estuvo aquí

/**
 * @author Jay Vega
 */
public class AccountActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.fab3)
    FloatingActionButton fab3;


    /**
     * ButterKnife dependence.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        checkPermissionsToApp(Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.RC_CAMERA);
    }


    @OnClick(R.id.fab1)
    public void onViewClicked_SCAN() {
        Toast.makeText(AccountActivity.this, "HOLA", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fab3)
    public void onViewClicked_LOGOUT() {
        signOut();
    }

    /**
     * This method sign out of Firebase.
     */
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkPermissionsToApp(String permissionStr, int requestPermission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(AccountActivity.this, permissionStr) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AccountActivity.this, new String[]{permissionStr}, requestPermission);
            }
            return;
        }
    }

    private void openScan() {
        checkPermissionsToApp(Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.RC_CAMERA);
        IntentIntegrator intentIntegrator = new IntentIntegrator(AccountActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("LECTOR DE QR");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }



}
