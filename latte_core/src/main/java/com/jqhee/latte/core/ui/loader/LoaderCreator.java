package com.jqhee.latte.core.ui.loader;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

public class LoaderCreator {

    // 保存创建的Indicators
    private static  final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();

    static AVLoadingIndicatorView create(String type, Context context) {
        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);
        // 是否已经创建过 Indicator， 有直接在集合中取，没有则创建
        if (LOADING_MAP.get(type) == null) {
            final Indicator indicator = getIndicator(type);
            LOADING_MAP.put(type, indicator);
        }
        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));
        return avLoadingIndicatorView;

    }

    private static Indicator getIndicator(String name) {
        if (name == null || name.isEmpty()) {
            return  null;
        }
        //通过反射获取包名如：com.wang.avi.indicators.BallPulseIndicator
        // 字符串拼接，性能更高
        final StringBuilder drawableClassName = new StringBuilder();
        //说明传入的是类名
        if (!name.equals(".")) {
            // 拼接完整的包名
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }
        drawableClassName.append(name);
        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());
            return (Indicator) drawableClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


}
