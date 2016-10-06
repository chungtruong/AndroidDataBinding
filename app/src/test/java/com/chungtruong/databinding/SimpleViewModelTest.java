package com.chungtruong.databinding;

import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;

import com.chungtruong.databinding.viewmodel.SimpleViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertEquals;

/**
 * Created by chung.truong on 10/5/2016.
 */
@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE)
public class SimpleViewModelTest {

    SimpleViewModel model;
    TextWatcher textWatcher;

    @Before
    public void setUp() {
        model = new SimpleViewModel();
        textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                model.numberObject.setValue1(Float.parseFloat(editable.toString()));
                model.numberObject.setValue2(Float.parseFloat(editable.toString()));
            }
        };
    }

    @Test
    public void testAddingValue() {
        SpannableStringBuilder number = new SpannableStringBuilder();
        number.append("1");
        textWatcher.beforeTextChanged(number, 0, 1, 1);
        assertEquals("1", number.toString());
        number.append("1");
        textWatcher.afterTextChanged(number);
        assertEquals("11", number.toString());
        assertEquals(11.0, model.numberObject.getValue1(), 1.0);
        assertEquals(11.0, model.numberObject.getValue2(), 1.0);
        assertEquals(22.0, model.numberObject.total(), 1.0);
        number.append("1");
        textWatcher.afterTextChanged(number);
        assertEquals(111.0, model.numberObject.getValue1(), 1.0);
        assertEquals(111.0, model.numberObject.getValue2(), 1.0);
        assertEquals(222.0, model.numberObject.total(), 1.0);
    }

}
