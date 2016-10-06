package com.chungtruong.databinding.viewmodel;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.chungtruong.databinding.model.NumberObject;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class SimpleViewModel {

    public NumberObject numberObject;

    public SimpleViewModel() {
        numberObject = new NumberObject();
    }

    public void onDestroy() {
        numberObject = null;
        getFirstValueTextWatcher = null;
        getSecondValueTextWatcher = null;
    }

    public TextWatcher getFirstValueTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            float value = !TextUtils.isEmpty(editable.toString()) ? Float.parseFloat(editable.toString()) : 0;
            if (numberObject != null) {
                numberObject.setValue1(value);
            }
        }
    };

    public TextWatcher getSecondValueTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            float value = !TextUtils.isEmpty(editable.toString()) ? Float.parseFloat(editable.toString()) : 0;
            if (numberObject != null) {
                numberObject.setValue2(value);
            }
        }
    };
}
