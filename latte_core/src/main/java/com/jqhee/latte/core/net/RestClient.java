package com.jqhee.latte.core.net;

import com.jqhee.latte.core.net.callback.IError;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;
import com.jqhee.latte.core.net.callback.RestRequestCallbacks;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.DELETE;

public class RestClient {

    private final String URL;
    private static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;
    private final RequestBody BODY;

    public RestClient(String url,
                      Map<String, Object> params,
                      IRequest irequest,
                      ISuccess isuccess,
                      IFailure ifailure,
                      IError ierror,
                      RequestBody body) {
        URL = url;
        PARAMS.putAll(params);
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
        BODY = body;
    }

    // 发起网络请求参数配置
    public static RestClientBuilder builder() {
        return  new RestClientBuilder();
    }

    // 具体的网络请求
    private  void request(HttpMethod method) {
        final RestService service = RestCreator.getRestService();
        Call<String> call = null;

        // 防止报空指针错误
        if (IREQUEST != null) {
            IREQUEST.onRequestStart();
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.delete(URL, PARAMS);
                break;
            case DELETE:
                break;
            case UPLOAD:
                break;
            case DOWNLOAD:
                break;
            default:
                break;
        }

        if (call != null) {
            // 在主线程执行，不推荐使用
            // call.execute();
            // 另起一条线程执行
            call.enqueue(getRequestCallback());
        }

    }

    // 设置网络请求回调接口
    private Callback<String> getRequestCallback() {
        return  new RestRequestCallbacks(
                IREQUEST,
                ISUCCESS,
                IFAILURE,
                IERROR
        );
    }

    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {
        request(HttpMethod.POST);
    }

    public final void put() {
        request(HttpMethod.PUT);
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }
}
