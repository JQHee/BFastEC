package com.jqhee.latte.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;


import com.jqhee.latte.core.app.AppUpdateService;
import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.net.callback.IRequest;
import com.jqhee.latte.core.net.callback.ISuccess;
import com.jqhee.latte.core.util.file.FileUtil;
import com.jqhee.latte.core.util.log.LatteLogger;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.ResponseBody;

/**
 * @author: wuchao
 * @date: 2017/11/6 22:57
 * @desciption:
 */

public class SaveFileTask extends AsyncTask<Object, Integer, File> {

    private final IRequest REQUEST;
    private final ISuccess SUCCESS;

    //声明publishProgress的更新标记
    private static final int PROGRESS_MAX = 0X1;
    private static final int UPDATE = 0X2;
    int contentLen;//声明要下载的文件总长

    public SaveFileTask(IRequest request, ISuccess success) {
        this.REQUEST = request;
        this.SUCCESS = success;
    }

    @Override
    protected File doInBackground(Object... params) {

        String downloadDir = (String) params[0];
        String extension = (String) params[1];
        ResponseBody body = (ResponseBody) params[2];
        String name = (String) params[3];
        InputStream is = body.byteStream();
        if (downloadDir == null || downloadDir.equals("")) {
            downloadDir = "down_loads";
        }
        if (extension == null || extension.equals("")) {
            extension = "";
        }

        File file;

        if (name == null) {
            file = FileUtil.createFileByTime(downloadDir, extension.toUpperCase(), extension);
        } else {
            file = FileUtil.createFile(downloadDir, name);
        }

        BufferedInputStream bis = null;
        FileOutputStream fos = null;
        BufferedOutputStream bos = null;

        try {

            URL url = new URL(downloadDir);
            //获取连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //获取下载文件的大小
            contentLen = connection.getContentLength();
            LatteLogger.i("Max-", String.valueOf(contentLen));
            //根据下载文件大小设置进度条最大值（使用标记区别实时进度更新）
            publishProgress(PROGRESS_MAX,contentLen);
            //循环下载（边读取边存入）
            bis = new BufferedInputStream(connection.getInputStream());
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);

            byte data[] = new byte[1024 * 4];
            int count;
            while ((count = bis.read(data)) != -1) {
                bos.write(data, 0, count);
                //实时更新下载进度（使用标记区别最大值）
                publishProgress(UPDATE,count);
            }
            bos.flush();
            fos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
                if (fos != null) {
                    fos.close();
                }
                if (bis != null) {
                    bis.close();
                }
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        LatteLogger.d(file);

        return  file;
    }

    // 在publishProgress被调用后，UI thread会调用这个方法
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        switch (values[0]){
            case PROGRESS_MAX: // 文件最大值
                LatteLogger.i("Max-", String.valueOf(values[1]));
                break;
                case UPDATE:
                    // 下载进度
                // progress.incrementProgressBy(values[1]);
                 int i=(values[1]*100)/contentLen;
                    LatteLogger.i("Current-", String.valueOf(i));
                    AppUpdateService.getInstance(Latte.getApplicationContext()).onProgress(0.3);
                    break;
        }
    }

    // doInBackground方法执行完后被UI thread执行
    @Override
    protected void onPostExecute(File file) {
        super.onPostExecute(file);
        if (SUCCESS != null) {
            SUCCESS.onSuccess(file.getPath());
        }
        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        AppUpdateService.getInstance(Latte.getApplicationContext()).onSuccess(file);
        // autoInstallApk(file);
    }

    /**
     * 安装Apk
     */
    private void autoInstallApk(File file) {
        /*
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
        */


        if (FileUtil.getExtension(file.getPath()).equals("apk")) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            //版本在7.0以上是不能直接通过uri访问的
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                LatteLogger.d("版本大于 N ，开始使用 fileProvider 进行安装");
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(Latte.getApplicationContext(), "com.jqhee.bfastec.example.fileprovider", file);
                //添加这一句表示对目标应用临时授权该Uri所代表的文件
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                LatteLogger.d("正常安装");
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.setDataAndType(Uri.fromFile(file),
                        "application/vnd.android.package-archive");
            }
            Latte.getApplicationContext().startActivity(intent);
        }
    }
}
