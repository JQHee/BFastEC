package com.jqhee.bfastec.example;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.jqhee.bfastec.example.testweb.event.TestEvent;
import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.net.interceptors.AddCookieInterceptor;
import com.jqhee.latte.core.net.interceptors.DebugInterceptor;
import com.jqhee.latte.core.net.interceptors.ProgressInterceptor;
import com.jqhee.latte.core.util.log.LatteLogger;
import com.jqhee.latte.ec.database.DatabaseManager;
import com.jqhee.latte.ec.icon.FontEcModule;

import java.util.ArrayList;

import okhttp3.Interceptor;


public class ExampleApp  extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 需要翻墙
        // initStetho();

        // 数据库初始化
        DatabaseManager.getInstance().init(this);

        ArrayList<Interceptor> array = new  ArrayList();
        // array.add(new DebugInterceptor("test", R.raw.test));
        array.add(new ProgressInterceptor());
        array.add(new StethoInterceptor());

        // 建造者模式
        Latte.init(this)
                .withApiHost("https://www.baidu.com")
                .withInterceptors(array)
                .withIcon(new FontAwesomeModule())
                .withIcon(new FontEcModule())
                .withWeChatAppId("")
                .withWeChatAppSecret("")
                .withJavaScriptInterface("native")
                .withWebHost("https://www.baidu.com")
                .withWebEvent("test", new TestEvent())
                .configure();
    }


    /**
     * 解决 使得这个dex的方法数量被限制在65535之内保错问题
     */
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void initStetho() {
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());
    }

}
