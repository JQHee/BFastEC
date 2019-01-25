package com.jqhee.latte.core.ui.recycler.autovalue;

import com.google.auto.value.AutoValue;

/**
 * autovalue 框架 导航栏颜色渐变使用
 */

@AutoValue
public abstract class RgbValue {

    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
