package com.jqhee.latte.core.app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.FileProvider;

import com.jqhee.latte.core.R;
import com.jqhee.latte.core.util.file.FileUtil;
import com.jqhee.latte.core.util.log.LatteLogger;

import java.io.File;

public class AppUpdateService {

    private NotificationManager notificationManager;
    private Notification notification; //下载通知进度提示
    private NotificationCompat.Builder builder;
    private boolean flag = false; //进度框消失标示 之后发送通知
    public static boolean isUpdate = false; //是否正在更新

    Context context;

    private static final class Holder {
        private static final AppUpdateService INSTANCE = new AppUpdateService();
        private static final AppUpdateService getThis(Context context) {
            INSTANCE.context = context;
            return INSTANCE;
        }
    }

    public static final AppUpdateService getInstance(Context context) {
        return Holder.getThis(context);
    }

    private AppUpdateService() {

    }

    /**
     * 文件开始下载时初始化通知
     */
    public void initNotification() {
        notificationManager = (NotificationManager) Latte.getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        builder = new NotificationCompat.Builder(Latte.getApplicationContext());
        builder.setContentTitle("正在更新...") //设置通知标题
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setLargeIcon(BitmapFactory.decodeResource(Latte.getApplicationContext().getResources(), R.mipmap.ic_launcher_round)) //设置通知的大图标
                .setDefaults(Notification.DEFAULT_LIGHTS) //设置通知的提醒方式： 呼吸灯
                .setPriority(NotificationCompat.PRIORITY_MAX) //设置通知的优先级：最大
                .setAutoCancel(false)//设置通知被点击一次是否自动取消
                .setContentText("下载进度:" + "0%")
                .setProgress(100, 0, false);
        notification = builder.build();//构建通知对象
    }

    /**
     * 文件下载成功
     */
    public void onSuccess(File file) {
        if (FileUtil.getExtension(file.getPath()).equals("apk")) {

            Intent intent = new Intent(Intent.ACTION_VIEW);
            //版本在7.0以上是不能直接通过uri访问的
            if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
                LatteLogger.d("版本大于 N ，开始使用 fileProvider 进行安装");
                // 由于没有在Activity环境下启动Activity,设置下面的标签
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                //参数1 上下文, 参数2 Provider主机地址 和配置文件中保持一致   参数3  共享的文件
                Uri apkUri = FileProvider.getUriForFile(context, "com.jqhee.bfastec.example.fileprovider", file);
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
            builder.setContentTitle("下载完成")
                    .setContentText("点击安装")
                    .setAutoCancel(true);//设置通知被点击一次是否自动取消
            PendingIntent pi = PendingIntent.getActivity(context, 0, intent, 0);
            notification = builder.setContentIntent(pi).build();
            notificationManager.notify(1, notification);
        }
    }

    /**
     * 下载进度条显示
     * @param fraction 小数 当前下载长度/文件总长
     */
    public void onProgress(double fraction) {
        builder.setProgress(100, (int) (fraction * 100), false);
        builder.setContentText("下载进度:" + (int) (fraction * 100) + "%");
        notification = builder.build();
        notificationManager.notify(1, notification);
    }

    /**
     * apk 安装 取消通知
     */
    public void installAPKCancle() {
        notificationManager.cancel(1);
    }
}
