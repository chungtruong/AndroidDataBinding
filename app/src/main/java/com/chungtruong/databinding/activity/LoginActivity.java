package com.chungtruong.databinding.activity;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chungtruong.databinding.R;
import com.chungtruong.databinding.databinding.ActivityLoginBinding;
import com.chungtruong.databinding.viewmodel.LoginViewModel;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class LoginActivity extends AppCompatActivity implements LoginViewModel.OnLoginClickedListener {

    private ActivityLoginBinding binding;
    private LoginViewModel model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        model = new LoginViewModel(this);
        binding.setLoginViewModel(model);

        binding.password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.unbind();
        model.onDestroy();

    }

    @Override
    public void onLoginClicked() {
        Toast.makeText(LoginActivity.this, "Login clicked", Toast.LENGTH_SHORT).show();
    }
}
