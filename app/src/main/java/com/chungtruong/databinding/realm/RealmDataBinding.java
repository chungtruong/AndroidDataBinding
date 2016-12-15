package com.chungtruong.databinding.realm;

import io.realm.RealmChangeListener;

/**
 * Created by chung.truong on 12/13/2016.
 */

public interface RealmDataBinding {

    interface Factory {
        RealmChangeListener create();
    }

    RealmDataBinding.Factory FACTORY = new Factory() {
        @Override
        public RealmChangeListener create() {
            return new RealmChangeListener() {
                @Override
                public void onChange(Object element) {
                    if (element instanceof RealmDataBinding) {
                        ((RealmDataBinding) element).notifyChange();
                    }
                }
            };
        }
    };

    void notifyChange();
}
