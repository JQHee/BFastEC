package com.jqhee.bfastec.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.jqhee.latte.core.delegates.LatteDelegate;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setupLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }
}
