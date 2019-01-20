package com.jqhee.latte.ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.util.Patterns;
import android.view.View;

import com.jqhee.fast.ec.R;
import com.jqhee.fast.ec.R2;
import com.jqhee.latte.core.delegates.LatteDelegate;

import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;

public class SignUpDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mNameInputEditText;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmailInputEditText;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhoneInputEditText;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPasswordInputEditText;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePasswordInputEditText;

    @OnClick(R2.id.btn_sign_up)
    void onClickRegisterButton() {
        if (checkForm()) {

        }
    }

    @OnClick(R2.id.tv_link_sign_up)
    void onClickLoginLinkText() {
        getSupportDelegate().start(new SignInDelegate());
    }

    private boolean checkForm() {
        boolean isCheckResult = true;

        String nameText = mNameInputEditText.getText().toString();
        String emailText = mEmailInputEditText.getText().toString();
        String phoneText = mPhoneInputEditText.getText().toString();
        String passwordText = mPasswordInputEditText.getText().toString();
        String rePasswordText = mRePasswordInputEditText.getText().toString();

        if (nameText.isEmpty()) {
            mNameInputEditText.setError("请填写用户名");
            isCheckResult = false;
        } else {
            mNameInputEditText.setError(null);
        }

        if (emailText.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(emailText).matches()) {
            mEmailInputEditText.setError("请填写正确的邮箱");
            isCheckResult = false;
        } else {
            mEmailInputEditText.setError(null);
        }

        if (phoneText.isEmpty() || phoneText.length() < 11) {
            mPhoneInputEditText.setError("请填写正确的手机号");
            isCheckResult = false;
        } else {
            mPhoneInputEditText.setError(null);
        }

        if (passwordText.isEmpty() || passwordText.length() < 6) {
            mPasswordInputEditText.setError("请填写至少6位密码");
            isCheckResult = false;
        } else {
            mPasswordInputEditText.setError(null);
        }

        if (!rePasswordText.isEmpty() && rePasswordText == passwordText) {
            mRePasswordInputEditText.setError("两次密码输入不一致");
            isCheckResult = false;
        } else {
            mRePasswordInputEditText.setError(null);
        }

        return  isCheckResult;
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
