package com.chungtruong.databinding.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by chung.truong on 10/5/2016.
 */

public class NumberObject extends BaseObservable {

    private float value1;
    private float value2;

    @Bindable
    public float getValue1() {
        return value1;
    }

    @Bindable
    public float getValue2() {
        return value2;
    }

    public void setValue1(float value1) {
        this.value1 = value1;
        notifyChange();
    }

    public void setValue2(float value2) {
        this.value2 = value2;
        notifyChange();
    }

    public float total() {
        return value1 + value2;
    }
}
