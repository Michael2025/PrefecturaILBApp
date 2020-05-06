package com.example.prefecturailb.moduleLogin.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prefecturailb.AccountActivity;
import com.example.prefecturailb.R;
import com.example.prefecturailb.common.pojo.User;
import com.example.prefecturailb.moduleLogin.LoginPresenter;
import com.example.prefecturailb.moduleLogin.LoginPresenterClass;
import com.example.prefecturailb.moduleLogin.utils.LoginValidations;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Jay Vega
 */
public class MainActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter mPresenter;
    private LoginValidations mValidations;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    /**
     * ButterKnife Dependence.
     */
    @BindView(R.id.btnSignIn)
    Button btnSignIn;
    @BindView(R.id.edPassword)
    TextInputEditText edPassword;
    @BindView(R.id.tilPassword)
    TextInputLayout tilPassword;
    @BindView(R.id.edEmail)
    TextInputEditText edEmail;
    @BindView(R.id.tilEmail)
    TextInputLayout tilEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configTheme();
        mPresenter = new LoginPresenterClass(this);
        mPresenter.onCreate();
        mPresenter.getStatusAuth();
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mValidations=new LoginValidations();
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


    /**
     * This method repairs the splash theme to default theme of the application.
     */
    private void configTheme() {
        setTheme(R.style.MyTheme_DayNight);
    }




    /**
     * This theme verify the EditTexts is not empty and verify the email format
     */



    @OnClick(R.id.btnSignIn)
    public void onViewClicked() {
        User user = null;
        if (!mValidations.validations(edEmail,tilEmail,edPassword,tilPassword)){
            user.setEmail(edEmail.getText().toString().trim());
            user.setPassword(edPassword.getText().toString().trim());
            mPresenter.signIn(user);
        }
    }

    @Override
    public void showProgress() {
     progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void disableUIComponents() {
        final boolean flagDisable=false;
        edEmail.setEnabled(flagDisable);
        edPassword.setEnabled(flagDisable);
        btnSignIn.setEnabled(flagDisable);
    }

    @Override
    public void enableUIComponents() {
        final boolean flagDisable=true;
        edEmail.setEnabled(flagDisable);
        edPassword.setEnabled(flagDisable);
        btnSignIn.setEnabled(flagDisable);
    }

    @Override
    public void openAccountActivity() {
        Intent intent =new Intent(MainActivity.this, AccountActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onShowError(int resMsg) {
        Toast.makeText(this, resMsg, Toast.LENGTH_SHORT).show();
    }
}