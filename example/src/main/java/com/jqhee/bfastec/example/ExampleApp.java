package com.jqhee.bfastec.example;

import android.app.Application;

import com.jqhee.latte.core.app.Latte;

public class ExampleApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // 建造者模式
        Latte.init(this)
                .withApiHost("")
                .configure();
    }

}
