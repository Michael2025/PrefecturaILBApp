package com.example.prefecturailb.moduleAccount.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.prefecturailb.R;
import com.example.prefecturailb.moduleLogin.view.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
//Franku estuvo aquí

/**
 * @author Jay Vega
 */
public class AccountActivity extends AppCompatActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    /**
     * ButterKnife dependence.
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                Toast.makeText(AccountActivity.this, "A FUTURO", Toast.LENGTH_SHORT).show();
                break;
            case R.id.log_out:
                signOut();
                break;
        }
        return super.onOptionsItemSelected(item);
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
}
