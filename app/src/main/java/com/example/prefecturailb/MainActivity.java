package com.example.prefecturailb;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.prefecturailb.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import java.util.regex.Pattern;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author Jay Vega
 */
public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;//get the authentication value of firebase.
    User user;//get the user Model

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
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configTheme();
        setContentView(R.layout.activity_main);
        isLogin();
        ButterKnife.bind(this);
    }

    /**
     * This method repairs the splash theme to default theme of the application.
     */
    private void configTheme() {
        setTheme(R.style.MyTheme_DayNight);
    }

    /**
     * This method verify if the user is Authenticated in firebase to redirect the accountActivity.
     */
    private void isLogin() {
        mAuth = FirebaseAuth.getInstance();
        mAuthListener = (FirebaseAuth.AuthStateListener) (FirebaseAuth) -> {
            if (FirebaseAuth.getCurrentUser() != null) {
                Toast.makeText(this, "" + FirebaseAuth.getCurrentUser().getUid(), Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(MainActivity.this, AccountActivity.class);
                startActivity(intent);
                finish();
            }
        };
    }

    /**
     * This method verify the email format.
     * @param email get the string to compare if is email format.
     * @return returns false or true.
     */
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    /**
     * This theme verify the EditTexts is not empty and verify the email format
     */
    private void validations() {
        user= new User();
        boolean isemtyEmail=false,isemtyPassword=false ;
        if (TextUtils.isEmpty(edEmail.getText().toString().trim())){
            tilEmail.setError("Ingrese su correo");
            isemtyEmail=true;
        }else {
            tilEmail.setError(null);
            user.setEmail(edEmail.getText().toString().trim());
            isemtyEmail=false;
        }
        if (TextUtils.isEmpty(edPassword.getText().toString().trim())){
            tilPassword.setError("Ingrese su contraseña");
            isemtyPassword=true;
        }else {
            tilPassword.setError(null);
            user.setPassword(edPassword.getText().toString().trim());
            isemtyPassword=false;
        }
        if (!isemtyEmail && !isemtyPassword){
            if (validarEmail(edEmail.getText().toString().trim())){
                signIn(user.getEmail(),user.getPassword());
            }else {
                tilEmail.setError("Ingrese su correo con formato");
            }

        }
    }

    /**
     * This method permits to SignIn to Firebase.
     * @param email email to verify in firebase Authentication.
     * @param password password to verify in firebase Authentication.
     */
    private void signIn(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, redirects to
                            Intent intent =new Intent(MainActivity.this, AccountActivity.class);
                            startActivity(intent);
                            finish();
                        }else if (task.getException() instanceof FirebaseNetworkException){
                            //Verify the internet connection.
                            Toast.makeText(MainActivity.this, R.string.error_internet,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if (task.getException() instanceof FirebaseAuthEmailException){
                            // Validates if email is correct.
                            Toast.makeText(MainActivity.this, R.string.error_email,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){
                            //Verify the credential is correct.
                             Toast.makeText(MainActivity.this, R.string.error_invalid_credencial,
                             Toast.LENGTH_SHORT).show();
                        }else if (task.getException() instanceof FirebaseAuthInvalidUserException){
                            //Verify if the user exist.
                            Toast.makeText(MainActivity.this, R.string.error_user_not_register,
                                    Toast.LENGTH_SHORT).show();
                        }else {
                            //Verify other error.
                            Toast.makeText(MainActivity.this, R.string.error_unknown,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick(R.id.btnSignIn)
    public void onViewClicked() {
        validations();
    }
}