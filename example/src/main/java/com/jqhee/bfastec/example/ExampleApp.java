package com.jqhee.bfastec.example;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.net.interceptors.AddCookieInterceptor;
import com.jqhee.latte.core.net.interceptors.DebugInterceptor;
import com.jqhee.latte.core.net.interceptors.ProgressInterceptor;
import com.jqhee.latte.core.util.log.LatteLogger;

import java.util.ArrayList;

import okhttp3.Interceptor;


public class ExampleApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 日志打印初始化
        LatteLogger.setup();
        initStetho();

        ArrayList<Interceptor> array =new  ArrayList();
        // array.add(new DebugInterceptor("test", R.raw.test));
        array.add(new ProgressInterceptor());
        array.add(new StethoInterceptor());

        // 建造者模式
        Latte.init(this)
                .withApiHost("https://www.baidu.com")
                .withInterceptors(array)
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
