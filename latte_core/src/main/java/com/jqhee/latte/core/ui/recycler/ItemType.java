package com.jqhee.latte.core.ui.recycler;

/**
 * Enum 和 static final， 后者性能更高，看应用场景
 * 首页recycleView 多布局显示的类型
 */

public class ItemType {

    public static final int TEXT = 1;
    public static final int IMAGE = 2;
    public static final int TEXT_IMAGE = 3;
    public static final int BANNER = 4;
    public static final int VERTICAL_MENU_LIST = 5;
    public static final int SINGLE_BIG_IMAGE = 6;
}
