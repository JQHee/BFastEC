package com.hjq.latte.app;

public class Configurator {

    private Configurator() {

    }

    /*
     * 线程安全的懒汉单例模式
     * @return
     */
    public static Configurator getInstance() {
        return Holder.INSTANCE;
    }

    /*
    * 静态内部类单例模式的初始化
    * */
    private  static  class Holder {
        private  static  final Configurator INSTANCE = new Configurator();
    }
}
