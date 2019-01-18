package com.jqhee.latte.core.net;

import android.content.Context;

import com.jqhee.latte.core.net.callback.IError;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;
import com.jqhee.latte.core.ui.loader.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RestClientBuilder {

    // 建造者模式

    private  String mUrl = null;
    private  static final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();
    private  IRequest mIRequest = null;
    private  ISuccess mISuccess = null;
    private  IFailure mIFailure = null;
    private  IError mIError = null;
    private  RequestBody mBody = null;
    private  File mFile = null;
    private LoaderStyle mLoaderStyple = null;
    // 为了创建 dialog
    private  Context mContext = null;

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

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyple = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String filePath) {
        this.mFile = new File(filePath);
        return this;
    }

    // RestClient 发起网络请求参数配置
    public final RestClient builder() {
        return  new RestClient(mUrl, PARAMS, mIRequest, mISuccess, mIFailure, mIError, mBody, mFile, mLoaderStyple, mContext);
    }
}
