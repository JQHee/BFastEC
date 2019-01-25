package com.jqhee.latte.core.ui.refresh;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.jqhee.latte.core.app.Latte;
import com.jqhee.latte.core.util.log.LatteLogger;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RefreshHandler implements SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {

    private final SwipeRefreshLayout REFRESH_LAYOUT;
    private final PagingBean BEAN;
    // 为版本设置适配器
    private final RecyclerView RECYCLERVIEW;

    private RefreshHandler(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView, PagingBean bean) {
        this.REFRESH_LAYOUT = refreshLayout;
        REFRESH_LAYOUT.setOnRefreshListener(this);
        this.RECYCLERVIEW = recyclerView;
        this.BEAN = bean;
    }

    /**
     * 供外部调用
     * @param refreshLayout 刷新的控件
     * @param recyclerView 显示数据的
     * @param
     */
    public static RefreshHandler create(SwipeRefreshLayout refreshLayout, RecyclerView recyclerView) {
        return new RefreshHandler(refreshLayout, recyclerView, new PagingBean());
    }

    /**
     * 下拉刷新是调用
     */
    @Override
    public void onRefresh() {
        refresh();
    }

    /**
     * 下拉加载
     */
    @Override
    public void onLoadMoreRequested() {
        LatteLogger.d("加载更多");
    }


    private void refresh() {
        REFRESH_LAYOUT.setRefreshing(true);
        // 延迟
        Latte.getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //进行网络请求
                REFRESH_LAYOUT.setRefreshing(false);
                // firstPage("index.php");
            }
        }, 2000);
    }

    public void firstPage(String url) {
        BEAN.setDelayed(1000);
        /*
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .compose(Transformer.<String>switchSchedulers())
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String response) throws Exception {
                        if (!TextUtils.isEmpty(response)) {
                            final JSONObject object = JSON.parseObject(response);
                            BEAN.setTotal(object.getInteger("total"))
                                    .setPageSize(object.getInteger("page_size"));
                            //设置adapter
                            mAdapter = MultipleRecyclerAdapter.create(CONVERTER.setJsonData(response));
                            mAdapter.setOnLoadMoreListener(RefreshHandler.this, RECYCLERVIEW);
                            RECYCLERVIEW.setAdapter(mAdapter);
                            BEAN.addIndex();
                        }
                    }
                });
          */
    }

    private void paging(final String url) {
        final int pageSize = BEAN.getPageSize();
        final int currentCount = BEAN.getCurrentCount();
        final int total = BEAN.getTotal();
        final int index = BEAN.getPageIndex();
        /*
        if (mAdapter.getData().size() < pageSize || currentCount >= total) {
            mAdapter.loadMoreEnd(true);
        } else {
            Latte.getHandler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    RxRestClient.builder()
                            .url(url + index)
                            .build()
                            .get()
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Consumer<String>() {
                                @Override
                                public void accept(String s) throws Exception {
                                    mAdapter.addData(CONVERTER.setJsonData(s).convert());
                                    //累加数量
                                    BEAN.setCurrentCount(mAdapter.getData().size());
                                    mAdapter.loadMoreComplete();
                                    BEAN.addIndex();
                                }
                            });
                }
            }, 1000);
        }
        */
    }
}
