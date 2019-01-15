package com.jqhee.latte.core.app;

import java.util.HashMap;

public class Configurator {

    private  static  final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();

    private Configurator() {
        //.name() 是以字符串的形式输出出来 name()：返回实例名
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, false);
    }

    /*
     * 线程安全的懒汉单例模式
     * @return
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    // 返回配置项
    final HashMap<Object, Object> getLatteConfigs() {
        return LATTE_CONFIGS;
    }

    /*
    * 静态内部类单例模式的初始化
    */
    private  static  class Holder {
        private  static  final Configurator INSTANCE = new Configurator();
    }

    /**
     *  配置完成
     */
    public final void  configure() {
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    /**
     *  配置网络请求链接
     */
    public final  Configurator withApiHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.API_HOST, host);
        return  this;
    }

    /**
     *  检查配置项是否完成
     */
    private  void  checkConfiguration() {
        final  boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigKeys.CONFIG_READY);
        // 如果配置没有完成，抛出异常
        if (!isReady) {
            throw new RuntimeException("Configuration is not ready, call config");
        }
    }

    /**
     * @param key
     * @param <T>
     * @return 存储的是Object，通过泛型返回数据
     */
    @SuppressWarnings("unchecked")
    final  <T> T getConfiguration(Object key) {
        checkConfiguration();
        Object value = LATTE_CONFIGS.get(key);
        if (value == null) {
            throw new NullPointerException(key.toString() + "is NULL" );
        }
        return (T) LATTE_CONFIGS.get(key);
    }

}

