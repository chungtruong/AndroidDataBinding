package com.chungtruong.databinding;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;

import com.chungtruong.databinding.model.LoginRequest;
import com.chungtruong.databinding.util.CustomTextWatcher;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by chung.truong on 10/5/2016.
 */

@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class LoginViewModelTest {

    LoginRequest loginRequest;

    @Before
    public void setUp() {
        loginRequest = new LoginRequest();
    }

    @Test
    public void testInputEmail() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("abcdef");
        TextWatcher textWatcher = new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                loginRequest.setEmail(editable.toString());
            }
        };
        assertEquals("abcdef", builder.toString());
        textWatcher.afterTextChanged(builder);
        assertFalse(loginRequest.isEmailEmpty());
        assertFalse(loginRequest.isValidEmail());
        builder.clear();
        builder.append("hello@gmail.com");
        textWatcher.afterTextChanged(builder);
        assertTrue(loginRequest.isValidEmail());
    }

    @Test
    public void testInputPassword() {
        SpannableStringBuilder builder = new SpannableStringBuilder();
        builder.append("abcd");
        TextWatcher textWatcher = new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                loginRequest.setPassword(editable.toString());
            }
        };
        textWatcher.afterTextChanged(builder);
        assertFalse(loginRequest.isPasswordEmpty());
        assertFalse(loginRequest.isValidPassword());
        builder.clear();
        builder.append("abcd123");
        textWatcher.afterTextChanged(builder);
        assertTrue(loginRequest.isValidPassword());
    }

    @Test
    public void testInputValidData() {
        SpannableStringBuilder builder1 = new SpannableStringBuilder();
        builder1.append("abcdef");
        TextWatcher textWatcher1 = new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                loginRequest.setEmail(editable.toString());
            }
        };
        textWatcher1.afterTextChanged(builder1);

        SpannableStringBuilder builder2 = new SpannableStringBuilder();
        builder2.append("abcd");
        TextWatcher textWatcher2 = new CustomTextWatcher() {
            @Override
            public void afterTextChanged(Editable editable) {
                super.afterTextChanged(editable);
                loginRequest.setPassword(editable.toString());
            }
        };
        textWatcher2.afterTextChanged(builder2);
        assertFalse(loginRequest.isValidInput());

        builder1.clear();
        builder1.append("hello@gmail.com");
        textWatcher1.afterTextChanged(builder1);
        assertFalse(loginRequest.isValidInput());

        builder2.clear();
        builder2.append("abcd123");
        textWatcher2.afterTextChanged(builder2);
        assertTrue(loginRequest.isValidInput());
    }
}
