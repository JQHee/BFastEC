package com.jqhee.latte.core.ui.controls;

public class ButtonUtil {

    public static final long INTERVAL = 3000L; //防止连续点击的时间间隔
    private static long lastClickTime = 0L; //上一次点击的时间
    public synchronized static boolean isFastClick() {
        long time = System.currentTimeMillis();
        if ( time - lastClickTime < INTERVAL) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
