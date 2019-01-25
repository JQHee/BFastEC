package com.jqhee.latte.core.ui.recycler.decoration;

import android.support.annotation.ColorInt;
import com.choices.divider.DividerItemDecoration;

/**
 * recycle 的分割线
 */
public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
