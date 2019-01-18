package com.jqhee.bfastec.example;

import android.app.Application;

import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.util.log.LatteLogger;


public class ExampleApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 日志打印初始化
        LatteLogger.setup();

        // 建造者模式
        Latte.init(this)
                .withApiHost("")
                .configure();
    }

}
