package com.chungtruong.databinding.viewmodel;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.chungtruong.databinding.model.LoginRequest;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class LoginViewModel {

    public LoginRequest loginRequest;
    private OnLoginClickedListener onLoginClickedListener;

    public LoginViewModel(OnLoginClickedListener onLoginClickedListener) {
        this.onLoginClickedListener = onLoginClickedListener;
        loginRequest = new LoginRequest();
    }

    public void onDestroy() {
        loginRequest = null;
        onLoginClickedListener = null;
        getPasswordTextWatcher = null;
        getUsernameTextWatcher = null;
    }

    public TextWatcher getUsernameTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (loginRequest != null) {
                loginRequest.setEmail(editable.toString());
            }
        }
    };

    public TextWatcher getPasswordTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            if (loginRequest != null) {
                loginRequest.setPassword(editable.toString());
            }
        }
    };

    public void onLoginClicked(View view) {
        if (onLoginClickedListener != null) {
            onLoginClickedListener.onLoginClicked();
        }
    }

    public interface OnLoginClickedListener {
        void onLoginClicked();
    }
}
