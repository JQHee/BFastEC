package com.jqhee.latte.core.ui.camera;

import android.net.Uri;

import com.jqhee.latte.core.delegates.PermissionCheckerDelegate;
import com.jqhee.latte.core.util.file.FileUtil;


/**
 * @author: wuchao
 * @date: 2018/1/4 23:17
 * @desciption: 照相机调用类
 */

public class LatteCamera {

    public static Uri createCropFile() {
        return Uri.parse(FileUtil.createFile("crop_image",
                FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckerDelegate delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
