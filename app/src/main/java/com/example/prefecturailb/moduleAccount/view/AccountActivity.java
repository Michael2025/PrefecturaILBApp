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
import com.example.prefecturailb.moduleAccount.AccountPresenter;
import com.example.prefecturailb.moduleAccount.AccountPresenterClass;
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
public class AccountActivity extends AppCompatActivity implements AccountView{
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab1)
    FloatingActionButton fab1;
    @BindView(R.id.fab3)
    FloatingActionButton fab3;

    private AccountPresenter mPresenter;

    /**
     * ButterKnife dependence.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        mPresenter = new AccountPresenterClass(this);
        mPresenter.onCreate();
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestroy();
    }

    @OnClick(R.id.fab1)
    public void onViewClicked_SCAN() {
        mPresenter.openScan();
    }

    @OnClick(R.id.fab3)
    public void onViewClicked_LOGOUT() {
        mPresenter.signOut();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        mPresenter.onPermissionsResult(requestCode, permissions, grantResults);
    }

    private void checkPermissionsToApp(String permissionStr, int requestPermission) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(AccountActivity.this, permissionStr) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(AccountActivity.this, new String[]{permissionStr}, requestPermission);
            }
            return;
        }
    }

    @Override
    public void openScan() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(AccountActivity.this);
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
        intentIntegrator.setPrompt("LECTOR DE QR");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }


    @Override
    public void checkPermissionsToApp() {
        checkPermissionsToApp(Manifest.permission.WRITE_EXTERNAL_STORAGE, Constants.RC_CAMERA);
    }

    @Override
    public void onOpenLogin() {
        Intent intent = new Intent(AccountActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onError(int message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
