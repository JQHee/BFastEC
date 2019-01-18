package com.jqhee.bfastec.example;

import android.os.Bundle;

import com.jqhee.latte.core.activitys.ProxyActivity;
import com.jqhee.latte.core.delegates.LatteDelegate;
import com.jqhee.latte.ec.launcher.LauncherDelegate;

public class ExampleActivity extends ProxyActivity {


    @Override
    public LatteDelegate setRootDelegate() {
        // 加载启动页
        return new LauncherDelegate();
    }
}
