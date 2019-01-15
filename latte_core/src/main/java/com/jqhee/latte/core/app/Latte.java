package com.jqhee.latte.core.app;

import android.content.Context;

public class Latte {

    /**
     * 返回配置
     * @param  context
     */
    public  static Configurator init(Context context) {
        getConfigurator().getLatteConfigs().put(ConfigKeys.APPLICATION_CONTEXT.name(), context.getApplicationContext());
        getConfigurator().getLatteConfigs().put(ConfigKeys.APPLICATION.name(), context);
        return Configurator.getInstance();
    }

    public  static  Configurator getConfigurator() {
        return  Configurator.getInstance();
    }

    public static  <T> T getConfiguration(Object key) {
        return  getConfigurator().getConfiguration(key);
    }

    // 全局的Context
    public static  Context getApplicationContext() {
        return  getConfiguration(ConfigKeys.APPLICATION_CONTEXT);
    }

    public static  Context getApplication() {
        return  getConfiguration(ConfigKeys.APPLICATION);
    }


}
