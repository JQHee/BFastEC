package com.jqhee.latte.core.net;

import com.jqhee.latte.core.net.callback.IError;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

import retrofit2.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    // 建造者模式

    private  String mUrl;
    private  static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest;
    private  ISuccess mISuccess;
    private  IFailure mIFailure;
    private  IError mIError;
    private  RequestBody mBody;

    //  只允许 RestClientBuilder new
    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RestClientBuilder params(String key, Object object) {
        this.PARAMS.put(key, object);
        return this;
    }

    /**
     * json 方式提交数据
     */
    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    // RestClient 发起网络请求参数配置
    public final RestClient builder() {
        return  new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody);
    }
}
