package com.jqhee.latte.core.util.callback;

import android.support.annotation.NonNull;



public interface IGlobalCallback<T> {

    void executeCallback(@NonNull T args);
}
