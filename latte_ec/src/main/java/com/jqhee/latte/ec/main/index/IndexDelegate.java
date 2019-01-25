package com.jqhee.latte.ec.main.index;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.joanzapata.iconify.widget.IconTextView;
import com.jqhee.fast.ec.R;
import com.jqhee.fast.ec.R2;
import com.jqhee.latte.core.bottom.BottomItemDelegate;
import com.jqhee.latte.core.ui.refresh.RefreshHandler;

import butterknife.BindView;
import butterknife.OnClick;

public class IndexDelegate extends BottomItemDelegate {

    @BindView(R2.id.rv_index)
    RecyclerView mRecyclerView;
    @BindView(R2.id.srl_index)
    SwipeRefreshLayout mRefreshLayout;
    // @BindView(R2.id.icon_index_scan)
    // IconTextView mIconScan;
    @BindView(R2.id.et_search_view)
    AppCompatEditText mSearchView;
    @BindView(R2.id.icon_index_message)
    IconTextView mIconMessage;
    @BindView(R2.id.tb_index)
    Toolbar mToolbar;

    private RefreshHandler mRefreshHandler = null;

    // 绑定多个
    @OnClick({R2.id.et_search_view, R2.id.srl_index})
    void onClick(View view) {

    }

    /**
     * 刷新控件初始化
     */
    private void initRefreshLayout() {
        // 设置颜色
        mRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        // 设置位置
        mRefreshLayout.setProgressViewOffset(true, 120, 300);
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_index;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        mRefreshHandler = RefreshHandler.create(mRefreshLayout, mRecyclerView);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initRefreshLayout();
    }
}
