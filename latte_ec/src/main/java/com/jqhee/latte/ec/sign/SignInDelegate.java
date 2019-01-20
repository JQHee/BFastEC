package com.jqhee.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.jqhee.fast.ec.R;
import com.jqhee.fast.ec.R2;
import com.jqhee.latte.core.delegates.LatteDelegate;

import butterknife.BindView;
import butterknife.OnClick;

public class SignInDelegate extends LatteDelegate {


    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEailInputEditText;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPasswrodInputEditText;

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

    }
}
