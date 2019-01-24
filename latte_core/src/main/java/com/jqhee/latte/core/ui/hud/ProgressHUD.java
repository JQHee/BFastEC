package com.jqhee.latte.core.ui.hud;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.jqhee.latte.core.R;

public class ProgressHUD {

    private ProgressHUDDialog mProgressHUDDialog;
    private float mDimAmount;
    private int mWindowColor;
    private float mCornerRadius;//矩形的圆角程度
    private Context mContext;

    public ProgressHUD(Context context) {
        mContext = context;
        mProgressHUDDialog = new ProgressHUDDialog(context);
        mWindowColor = context.getResources().getColor(R.color.kprogresshud_default_color);
        mCornerRadius = 10;
        //转圈圈的圆形view
        View view = new CirView(mContext);
        mProgressHUDDialog.setView(view);
    }

    // 设置HUD的大小
    public ProgressHUD setSize(int width, int height) {
        mProgressHUDDialog.setSize(width, height);
        return this;
    }


    // 设置矩形的圆角程度
    public ProgressHUD setCornerRadius(float radius) {
        mCornerRadius = radius;
        return this;
    }

    // 展示
    public ProgressHUD show() {
        if (!isShowing()) {
            mProgressHUDDialog.show();
        }
        return this;
    }

    public boolean isShowing() {
        return mProgressHUDDialog != null && mProgressHUDDialog.isShowing();
    }
    // 隐藏
    public void dismiss() {
        if (mProgressHUDDialog != null && mProgressHUDDialog.isShowing()) {
            mProgressHUDDialog.dismiss();
        }
    }

    private class ProgressHUDDialog extends Dialog {
        private View mView;

        private FrameLayout mCustomViewContainer;
        private BackgroundLayout mBackgroundLayout;
        private int mWidth, mHeight;

        public ProgressHUDDialog(Context context) {
            super(context);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.kprogresshud_hud);
            Window window = getWindow();
            window.setBackgroundDrawable(new ColorDrawable(0));
            window.addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            WindowManager.LayoutParams layoutParams = window.getAttributes();
            layoutParams.dimAmount = mDimAmount;
            layoutParams.gravity = Gravity.CENTER;
            window.setAttributes(layoutParams);
            setCanceledOnTouchOutside(false);
            initViews();
        }

        private void initViews() {
            mBackgroundLayout = (BackgroundLayout) findViewById(R.id.background);
            mBackgroundLayout.setBaseColor(mWindowColor);
            mBackgroundLayout.setCornerRadius(mCornerRadius);
            if (mWidth != 0) {
                updateBackgroundSize();
            }

            mCustomViewContainer = (FrameLayout) findViewById(R.id.container);
            addViewToFrame(mView);
        }

        private void addViewToFrame(View view) {
            if (view == null) return;
            int wrapParam = ViewGroup.LayoutParams.WRAP_CONTENT;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(wrapParam, wrapParam);
            mCustomViewContainer.addView(view, params);
        }

        private void updateBackgroundSize() {
            ViewGroup.LayoutParams params = mBackgroundLayout.getLayoutParams();
            params.width = Helper.dpToPixel(mWidth, getContext());
            params.height = Helper.dpToPixel(mHeight, getContext());
            mBackgroundLayout.setLayoutParams(params);
        }

        public void setView(View view) {
            if (view != null) {
                mView = view;
                if (isShowing()) {
                    mCustomViewContainer.removeAllViews();
                    addViewToFrame(view);
                }
            }
        }

        public void setSize(int width, int height) {
            mWidth = width;
            mHeight = height;
            if (mBackgroundLayout != null) {
                updateBackgroundSize();
            }
        }
    }

}
