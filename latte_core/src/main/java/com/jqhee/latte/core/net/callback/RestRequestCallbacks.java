package com.jqhee.latte.core.net.callback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Callback 特别注意为retrofit2中的接口
 */

public class RestRequestCallbacks implements Callback<String> {

    private final IRequest IREQUEST;
    private final ISuccess ISUCCESS;
    private final IFailure IFAILURE;
    private final IError IERROR;

    public RestRequestCallbacks(IRequest irequest, ISuccess isuccess, IFailure ifailure, IError ierror) {
        IREQUEST = irequest;
        ISUCCESS = isuccess;
        IFAILURE = ifailure;
        IERROR = ierror;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) { // 请求成功
            if (call.isExecuted()) {
                // 判空，防止空指针异常
                if (ISUCCESS != null) {
                    ISUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (IERROR != null) {
                IERROR.onError(response.code(), response.message());
            }
        }
        // http请求结束
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }

    }

    @Override
    public void onFailure(Call<String> call, Throwable t) {
        if (IFAILURE != null) {
            IFAILURE.onFailure();
        }

        // http请求结束
        if (IREQUEST != null) {
            IREQUEST.onRequestEnd();
        }
    }
}
