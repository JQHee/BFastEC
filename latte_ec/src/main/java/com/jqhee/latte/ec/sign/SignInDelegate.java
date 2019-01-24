package com.jqhee.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.jqhee.fast.ec.R;
import com.jqhee.fast.ec.R2;
import com.jqhee.latte.core.delegates.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.toolbar_title)
    AppCompatTextView mNavTitleTextView = null;

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEailInputEditText = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPasswrodInputEditText = null;

    @OnClick(R2.id.btn_sign_in)
    void  onClickLoginButton() {
        // 登录
        if (checkForm()) {

        }
    }

    @OnClick(R2.id.tv_link_sign_in)
    void onClickRegisterLinkText() {
        getSupportDelegate().start(new SignUpDelegate());
    }

    private void initToolbar(View rootView) {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.id_toolbar);
        AppCompatTextView titleText = (AppCompatTextView) rootView.findViewById(R.id.toolbar_title);
        toolbar.setTitle("");
        mNavTitleTextView.setText(R.string.nav_sign_in_title);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        // 指定向上返回
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        // 自定义返回的图标
        // actionBar.setHomeAsUpIndicator(R.drawable.menu_back_bg);  //设置自定义的返回键图标
        actionBar.setDisplayHomeAsUpEnabled(true);

        /*
        * 自定义返回按钮和自定义actionBar类似
        */

        // 自定义actionBar
        /*
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); //Enable自定义的View
        actionBar.setCustomView(R.layout.actionbar_custom);//设置自定义的布局：actionbar_custom
        */

        // 如果不设置 onCreateOptionsMenu 不会调用
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Log.e(TAG, "onCreateOptionsMenu()");
        menu.clear();
        inflater.inflate(R.menu.toolbar_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int i = item.getItemId();
        if (i == android.R.id.home) {
            // getSupportFragmentManager().popBackStack();//suport.v4包
            getFragmentManager().popBackStack();
            Toast.makeText(getActivity(), "返回", Toast.LENGTH_SHORT).show();
            return true;
        } else if (i == R.id.action_share) {
            Toast.makeText(getActivity(), "分享", Toast.LENGTH_SHORT).show();
            return true;
        } else if (i == R.id.action_commit) {
            Toast.makeText(getActivity(), "完成", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 提交信息校验
     */
    private boolean checkForm() {
        boolean isCheckResult = true;

        String emailText = mEailInputEditText.getText().toString();
        String passwordText = mPasswrodInputEditText.getText().toString();

        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            mEailInputEditText.setError("错误的邮箱");
            isCheckResult = false;
        } else {
            // 不置空则不能消除错误
            mEailInputEditText.setError(null);
        }

        if (passwordText.isEmpty() || passwordText.length() < 6) {
            mPasswrodInputEditText.setError("请填写至少6位密码");
            isCheckResult = false;
        } else {
            mPasswrodInputEditText.setError(null);
        }
        return isCheckResult;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initToolbar(rootView);
    }
}
