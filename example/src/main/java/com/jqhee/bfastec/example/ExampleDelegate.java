package com.jqhee.bfastec.example;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.jqhee.latte.core.delegates.LatteDelegate;
import com.jqhee.latte.core.net.RestClient;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;

public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    // 测试网络请求
    private void testRestClient() {
        RestClient.builder()
                .url("")
                .params("" , "")
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {

                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {

                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .builder()
                .get();
    }
}
