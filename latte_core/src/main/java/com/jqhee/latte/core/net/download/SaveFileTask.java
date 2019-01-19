package com.jqhee.latte.core.net.download;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;


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
        autoInstallApk(file);
    }

    /**
     * 安装Apk
     */
    private void autoInstallApk(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {
            Intent install = new Intent();
            install.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            install.setAction(Intent.ACTION_VIEW);
            install.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            Latte.getApplicationContext().startActivity(install);
        }
    }
}
