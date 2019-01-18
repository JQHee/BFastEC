package com.jqhee.latte.core.net;

import com.jqhee.latte.core.app.ConfigKeys;
import com.jqhee.latte.core.app.Latte;

import java.util.ArrayList;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RestCreator {

    // 惰性加载, 静态内部类

    /**
     * 网络请求参数params
     */
    public  static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    private static final class ParamsHolder {
        private static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    /**
     *  Retrofit 对象创建
     */
    private static final class RetrofitHolder {
        private  static  final  String BASE_URL = (String) Latte.getLatteConfigurations().get(ConfigKeys.API_HOST.name());

        // ScalarsConverterFactory.create() 添加 String类型[ Scalars (primitives, boxed, and String)] 转换器
        private  static  final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    /**
     * OkHttpClient 对象创建
     */
    private static final class OKHttpHolder {
        // 网络请求超时时间
        private  static  final  int TIME_OUT = 60;

        private static final OkHttpClient.Builder BUILDER = RetrofitUrlManager.getInstance().with(new OkHttpClient.Builder());

        private static final ArrayList<Interceptor> INTERCEPTORS = Latte.getConfiguration(ConfigKeys.INTERCEPTOR);

        private static OkHttpClient.Builder addInterceptor() {
            if (INTERCEPTORS != null && !INTERCEPTORS.isEmpty()) {
                for (Interceptor interceptor : INTERCEPTORS) {
                    BUILDER.addInterceptor(interceptor);
                }
            }

            return BUILDER;
        }
        // TimeUnit.SECONDS 以秒为单位
        // okhttp可以添加拦截器 （可以用来设置请求的header）
        private static final OkHttpClient OK_HTTP_CLIENT = addInterceptor()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    /**
     * RestService 接口对象创建
     */
    public  static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    private static final class RestServiceHolder {
        private  static  final RestService REST_SERVICE = RetrofitHolder.RETROFIT_CLIENT
                .create(RestService.class);
    }
}
