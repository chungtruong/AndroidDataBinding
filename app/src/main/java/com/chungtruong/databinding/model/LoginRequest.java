package com.chungtruong.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.annotation.NonNull;

import com.chungtruong.databinding.util.TextHelper;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class LoginRequest extends BaseObservable {

    @NonNull
    private String email;
    @NonNull
    private String password;

    public LoginRequest() {
        email = "";
        password = "";
    }

    @NonNull
    public String getEmail() {
        return email;
    }

    @NonNull
    public String getPassword() {
        return password;
    }

    public void setEmail(@NonNull String email) {
        this.email = email;
        notifyChange();
    }

    public void setPassword(@NonNull String password) {
        this.password = password;
        notifyChange();
    }

    public boolean isValidEmail() {
        return TextHelper.isValidEmail(email);
    }

    public boolean isEmailEmpty() {
        return email != null && email.isEmpty();
    }

    public boolean isValidPassword() {
        return password != null && password.length() > 6;
    }

    public boolean isPasswordEmpty() {
        return password != null && password.isEmpty();
    }

    public boolean isValidInput() {
        return isValidEmail() && isValidPassword();
    }
}
