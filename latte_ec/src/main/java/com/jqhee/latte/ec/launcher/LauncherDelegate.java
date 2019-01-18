package com.jqhee.latte.ec.launcher;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.jqhee.fast.ec.R;
import com.jqhee.fast.ec.R2;
import com.jqhee.latte.core.delegates.LatteDelegate;

import butterknife.BindView;

public class LauncherDelegate extends LatteDelegate {

    @BindView(R2.id.tv_launcher_timer)
    AppCompatTextView mTvLauncherTimer;

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@NonNull Bundle savedInstanceState, View rootView) {

    }
}
