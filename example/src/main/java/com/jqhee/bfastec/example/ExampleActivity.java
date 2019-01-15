package com.jqhee.bfastec.example;

import android.os.Bundle;

import com.jqhee.latte.core.activitys.ProxyActivity;
import com.jqhee.latte.core.delegates.LatteDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        return new ExampleDelegate();
    }
}
