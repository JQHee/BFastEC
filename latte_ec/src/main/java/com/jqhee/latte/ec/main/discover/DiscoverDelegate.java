package com.jqhee.latte.ec.main.discover;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.jqhee.fast.ec.R;
import com.jqhee.latte.core.bottom.BottomItemDelegate;
import com.jqhee.latte.core.delegates.web.WebDelegateImpl;


public class DiscoverDelegate extends BottomItemDelegate {

    @Override
    public Object setLayout() {
        return R.layout.delegate_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View rootView) {

    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        // https://www.baidu.com

        final WebDelegateImpl delegate = WebDelegateImpl.createContent("一二三");
                // WebDelegateImpl.create("index.html");
        delegate.setTopDelegate(this.getParentDelegate());
        loadRootFragment(R.id.web_discovery_container, delegate);
    }
}
