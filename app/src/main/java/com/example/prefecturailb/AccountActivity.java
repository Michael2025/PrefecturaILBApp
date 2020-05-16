package com.example.prefecturailb;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.prefecturailb.moduleLogin.view.MainActivity;
import com.google.firebase.auth.FirebaseAuth;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//Franku estuvo aquí
/**
 * @author Jay Vega
 */
public class AccountActivity extends AppCompatActivity {
    /**
     * ButterKnife dependence.
     */
    @BindView(R.id.btnSignOut)
    Button btnSignOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
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

    @OnClick(R.id.btnSignOut)
    /**
     * This method set up the action of the signOut button.
     */
    public void onViewClicked() {
        signOut();
    }
}
