package com.jqhee.latte.ec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;

import com.bigkoo.convenientbanner.ConvenientBanner;
import com.bigkoo.convenientbanner.listener.OnItemClickListener;
import com.jqhee.fast.ec.R;
import com.jqhee.fast.ec.R2;
import com.jqhee.latte.core.app.AccountManager;
import com.jqhee.latte.core.app.IUserChecker;
import com.jqhee.latte.core.delegates.LatteDelegate;
import com.jqhee.latte.core.ui.launcher.LauncherHolderCreator;
import com.jqhee.latte.core.ui.launcher.ScrollLauncherTag;
import com.jqhee.latte.core.util.log.LatteLogger;
import com.jqhee.latte.core.util.storage.LattePreference;
import com.jqhee.latte.ec.sign.SignInDelegate;

import java.util.ArrayList;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.OnClick;

public class LauncherScrollDelegate extends LatteDelegate implements OnItemClickListener, ViewPager.OnPageChangeListener {

    @BindView(R2.id.convenientBanner)
    ConvenientBanner<Integer> mConvenientBanner;

    @BindView(R2.id.btn_skip)
    Button mButton;

    private static final ArrayList<Integer> INTEGERS = new ArrayList<>();
    private ILauncherListener mILauncherListener = null;

    @OnClick(R2.id.btn_skip)
    void skipButtonAction() {
        LatteLogger.d("Info", "点击");
        LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
        //检查用户是否登录APP
        AccountManager.checkAccount(new IUserChecker() {
            @Override
            public void onSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                }
            }

            @Override
            public void onNotSignIn() {
                if (mILauncherListener != null) {
                    mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                }
            }
        });
    }

    private void initBanner() {
        INTEGERS.add(R.mipmap.launcher_01);
        INTEGERS.add(R.mipmap.launcher_02);
        INTEGERS.add(R.mipmap.launcher_03);
        INTEGERS.add(R.mipmap.launcher_04);
        INTEGERS.add(R.mipmap.launcher_05);

        mConvenientBanner.setPages(new LauncherHolderCreator(), INTEGERS)
                //设置两个点图片作为翻页指示器，不设置则没有指示器，可以根据自己需求自行配合自己的指示器,
                // 不需要圆点指示器可用不设
                .setPageIndicator(new int[]{R.drawable.dot_normal, R.drawable.dot_focus})
                .setPageIndicatorAlign(ConvenientBanner.PageIndicatorAlign.CENTER_HORIZONTAL)
                .setOnPageChangeListener(this)
                .setOnItemClickListener(this)
                .setCanLoop(false);

        // 设置数据刷新
        // INTEGERS.remove(0);
        // mConvenientBanner.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher_scroll;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initBanner();
    }

    @Override
    public void onItemClick(int position) {
        //如果点击的是最后一个
        /*
        if (position == INTEGERS.size() - 1) {
            LattePreference.setAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name(), true);
            //检查用户是否登录APP
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
        */
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (position == INTEGERS.size() - 1) {
            mButton.setVisibility(View.VISIBLE);
            mConvenientBanner.setPointViewVisible(false);
        } else {
            mButton.setVisibility(View.GONE);
            mConvenientBanner.setPointViewVisible(true);
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
