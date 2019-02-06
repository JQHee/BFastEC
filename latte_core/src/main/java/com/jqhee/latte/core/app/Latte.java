package com.jqhee.latte.core.app;

import android.content.Context;
import android.os.Handler;

import java.util.HashMap;

public class Latte {

    /**
     * 返回配置
     * @param  context
     */
    public  static Configurator init(Context context) {
        getConfigurator().getLatteConfigurations().put(ConfigKeys.APPLICATION_CONTEXT, context.getApplicationContext());
        getConfigurator().getLatteConfigurations().put(ConfigKeys.APPLICATION, context);
        return Configurator.getInstance();
    }

    public  static  Configurator getConfigurator() {
        return  Configurator.getInstance();
    }

    public static  <T> T getConfiguration(Object key) {
         return  getConfigurator().getConfiguration(key);
    }

    public static HashMap<Object, Object> getLatteConfigurations() {
        return getConfigurator().getLatteConfigurations();
    }

    // 全局的Context
    public static  Context getApplicationContext() {
        return  getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static  Context getApplication() {
        return  getConfiguration(ConfigKeys.APPLICATION);
    }

    public static Handler getHandler() {
        return getConfiguration(ConfigKeys.HANDLER);
    }


}
