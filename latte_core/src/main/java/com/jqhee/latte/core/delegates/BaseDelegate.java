package com.jqhee.latte.core.delegates;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

// 仿苹果的侧滑手势
public abstract class BaseDelegate extends SwipeBackFragment {

    private Unbinder mUnbinder = null;

    public abstract Object setupLayout();

    public abstract void onBindView(@NonNull Bundle savedInstanceState, View rootView);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setupLayout() instanceof  Integer) {
            rootView = inflater.inflate((Integer) setupLayout(),container, false);
        } else if (setupLayout() instanceof  View) {
            rootView = (View) setupLayout();
        }
        if (rootView != null) {
            mUnbinder = ButterKnife.bind(this, rootView);
            onBindView(savedInstanceState, rootView);

        }
        return rootView;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // 解除绑定
        if (mUnbinder != null) {
            mUnbinder.unbind();
        }
    }
}
