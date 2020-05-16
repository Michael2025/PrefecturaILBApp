package com.example.prefecturailb.moduleLogin.utils;

import android.text.TextUtils;
import android.util.Patterns;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class LoginValidations {
    /**
     * This method verify the email format.
     *
     * @param email get the string to compare if is email format.
     * @return returns false or true.
     */
    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean validations(TextInputEditText edEmail, TextInputLayout tilEmail , TextInputEditText edPassword, TextInputLayout tilPassword) {
        boolean isEmpty = false;
        if (TextUtils.isEmpty(edEmail.getText().toString().trim())) {
            tilEmail.setError("Ingrese su correo");
            isEmpty = true;
        }else if(!validarEmail(edEmail.getText().toString().trim())){
            tilEmail.setError("Ingrese su correo con formato");
            isEmpty = true;
        }else {
            tilEmail.setError(null);
            isEmpty = false;
        }
        if (TextUtils.isEmpty(edPassword.getText().toString().trim())) {
            tilPassword.setError("Ingrese su contraseña");
            isEmpty = true;
        } else {
            tilPassword.setError(null);
            isEmpty = false;
        }
        return isEmpty;
    }
}
