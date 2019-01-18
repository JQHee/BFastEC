package com.jqhee.bfastec.example;

import android.os.Bundle;

import com.jqhee.latte.core.activitys.ProxyActivity;
import com.jqhee.latte.core.delegates.LatteDelegate;
import com.jqhee.latte.ec.launcher.ILauncherListener;
import com.jqhee.latte.ec.launcher.LauncherDelegate;
import com.jqhee.latte.ec.launcher.OnLauncherFinishTag;
import com.jqhee.latte.ec.sign.SignInDelegate;

public class ExampleActivity extends ProxyActivity implements ILauncherListener {


    @Override
    public LatteDelegate setRootDelegate() {
        // 加载启动页
        return new LauncherDelegate();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED: // 已经登录
                break;
            case NOT_SIGNED: // 已经未登录
                // Toast.makeText(this, "启动结束，用户没登录", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new SignInDelegate());
                break;
        }
    }
}
