package com.jqhee.bfastec.example;

import android.app.DownloadManager;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.TextView;

import com.jqhee.bfastec.example.R;

import com.jqhee.latte.core.app.AppUpdateService;
import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.delegates.LatteDelegate;
import com.jqhee.latte.core.net.RestClient;
import com.jqhee.latte.core.net.body.ProgressListener;
import com.jqhee.latte.core.net.callback.IFailure;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;
import com.jqhee.latte.core.util.log.LatteLogger;


import butterknife.BindView;
import butterknife.OnClick;

public class ExampleDelegate extends LatteDelegate implements ProgressListener {

    private TextView mTextView;
    private DownloadManager mDownloadManager;

    /**
     * 显示更新的弹窗
     */
    private void showUpdateDialog() {

        final AlertDialog dialog =  new AlertDialog.Builder(getProxyActivity())
                .setTitle("版本更新")
                .setMessage("发现新的app版本，请及时更新")
                .setNegativeButton("暂不更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        downloadApk();
                    }
                })
                .create();
        dialog.show();
        // 点击背景不去除
        dialog.setCancelable(false);
        // dialog.setCanceledOnTouchOutside(false);
    }

    private void downloadApk() {
        RestClient.builder()
                .url("http://www.gjfenxiao.com:8017/api/upgrade/get_latest_apk")
                .params("" , "")
                .downloadSetting(null, null, "ganjie.apk")
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                        AppUpdateService.getInstance(Latte.getApplicationContext()).initNotification();
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
                .download();
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {
        mTextView = (TextView)rootView.findViewById(R.id.tv_example_test);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showUpdateDialog();
            }
        });
        // testRestClient();
    }

    // 测试网络请求
    private void testRestClient() {
        RestClient.builder()
                .url("http://www.gjfenxiao.com:8017/api/upgrade/get_latest_apk")
                .params("" , "")
                .downloadSetting(null, null, "ganjie.apk")
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
                .download();
    }

    @Override
    public void onProgress(long progress, long total, long speed, boolean done) {
        LatteLogger.i("progress", String.valueOf(progress));
    }
}
