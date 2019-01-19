package com.jqhee.latte.ec.sign;

public interface ISignListener {

    // 登录成功
    void onSignInSuccess(String response);

    // 注册成功
    void onSignUpSuccess(String response);
}
