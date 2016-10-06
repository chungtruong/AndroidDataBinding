package com.chungtruong.databinding.util;

import android.text.TextUtils;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class TextHelper {

    public final static boolean isValidEmail(CharSequence charSequence) {
        return !TextUtils.isEmpty(charSequence) && android.util.Patterns.EMAIL_ADDRESS.matcher(charSequence).matches();
    }
}
