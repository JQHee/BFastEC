package com.jqhee.latte.core.app;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.joanzapata.iconify.IconFontDescriptor;
import com.joanzapata.iconify.Iconify;
import com.jqhee.latte.core.delegates.web.event.Event;
import com.jqhee.latte.core.delegates.web.event.EventManager;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

import okhttp3.Interceptor;

public class Configurator {

    private  static  final HashMap<Object, Object> LATTE_CONFIGS = new HashMap<>();
    private static final ArrayList<IconFontDescriptor> ICONS = new ArrayList<>();
    // okhttp拦截器
    private static final ArrayList<Interceptor> INTERCEPTORS = new ArrayList<>();

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
    final HashMap<Object, Object> getLatteConfigurations() {
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
        initIcons();
        // 日志初始化
        Logger.addLogAdapter(new AndroidLogAdapter());
        LATTE_CONFIGS.put(ConfigKeys.CONFIG_READY, true);
    }

    private void initIcons() {
        if (ICONS.size() > 0) {
            final Iconify.IconifyInitializer initializer = Iconify.with(ICONS.get(0));
            for (int i = 1; i < ICONS.size(); i++) {
                initializer.with(ICONS.get(i));
            }
        }
    }

    public final Configurator withIcon(IconFontDescriptor descriptor) {
        ICONS.add(descriptor);
        return this;
    }

    /**
     * 微信相关配置
     */
    public final Configurator withWeChatAppId(String appId) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_ID, appId);
        return this;
    }

    public final Configurator withWeChatAppSecret(String appSecret) {
        LATTE_CONFIGS.put(ConfigKeys.WE_CHAT_APP_SECRET, appSecret);
        return this;
    }

    public final Configurator withActivity(Activity activity) {
        LATTE_CONFIGS.put(ConfigKeys.ACTIVITY, activity);
        return this;
    }


    /**
     * 网页内容配置
     */
    public final Configurator withJavaScriptInterface(String name) {
        LATTE_CONFIGS.put(ConfigKeys.JAVASCRIPT_INTERFACE, name);
        return this;
    }

    public final Configurator withWebEvent(@NonNull String name, @NonNull Event event) {
        final EventManager manager = EventManager.getInstance();
        manager.addEvent(name, event);
        return this;
    }

    public final Configurator withWebHost(String host) {
        LATTE_CONFIGS.put(ConfigKeys.WEB_HOST, host);
        return this;
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
     * 拦截器
     */
    public final Configurator withInterceptor(Interceptor interceptor) {
        INTERCEPTORS.add(interceptor);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
    }

    public final Configurator withInterceptors(ArrayList<Interceptor> interceptors) {
        INTERCEPTORS.addAll(interceptors);
        LATTE_CONFIGS.put(ConfigKeys.INTERCEPTOR, INTERCEPTORS);
        return this;
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

