package com.jqhee.bfastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.util.log.LatteLogger;


public class ExampleApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 日志打印初始化
        LatteLogger.setup();
        initStetho();

        // 建造者模式
        Latte.init(this)
                .withApiHost("")
                .configure();
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
