package com.jqhee.latte.ec.sign;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jqhee.latte.core.app.AccountManager;
import com.jqhee.latte.ec.database.DatabaseManager;
import com.jqhee.latte.ec.database.UserProfile;


public class SignHandler {

    // 登录成功
    /**
     * @param response 登录成功返回的json
     */
    static void onSignIn(String response, ISignListener signListener) {
        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        // 不能直接使用insert，主键相同重复插入会报错
        DatabaseManager.getInstance().getDao().insertOrReplace(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignInSuccess();

    }

    // 注册成功
    /**
     * @param response 注册成功返回的json
     */
    static void onSignUp(String response, ISignListener signListener) {

        final JSONObject profileJson = JSON.parseObject(response).getJSONObject("data");
        final long userId = profileJson.getLong("userId");
        final String name = profileJson.getString("name");
        final String avatar = profileJson.getString("avatar");
        final String gender = profileJson.getString("gender");
        final String address = profileJson.getString("address");

        final UserProfile profile = new UserProfile(userId, name, avatar, gender, address);
        // 不能直接使用insert，主键相同重复插入会报错
        DatabaseManager.getInstance().getDao().insertOrReplace(profile);

        //已经注册并登录成功了
        AccountManager.setSignState(true);
        signListener.onSignUpSuccess();
    }

}
