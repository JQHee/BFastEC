package com.jqhee.latte.ec.main;

import android.graphics.Color;


import com.jqhee.latte.core.bottom.BaseBottomDelegate;
import com.jqhee.latte.core.bottom.BottomItemDelegate;
import com.jqhee.latte.core.bottom.BottomTabBean;
import com.jqhee.latte.core.bottom.ItemBuilder;
import com.jqhee.latte.ec.main.discover.DiscoverDelegate;
import com.jqhee.latte.ec.main.index.IndexDelegate;
import com.jqhee.latte.ec.main.sort.SortDelegate;

import java.util.LinkedHashMap;

/**
 * @author: wuchao
 * @date: 2017/12/3 21:44
 * @desciption:
 */

public class EcBottomDelegate extends BaseBottomDelegate {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemDelegate> setItems(ItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemDelegate> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortDelegate());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new DiscoverDelegate());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexDelegate());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexDelegate());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexDelegate() {
        return 0;
    }

    @Override
    public int setClickedColor() {
        return Color.parseColor("#ffff8800");
    }
}
