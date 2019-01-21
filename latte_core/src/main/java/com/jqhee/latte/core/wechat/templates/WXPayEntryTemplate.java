package com.jqhee.latte.core.wechat.templates;

import android.widget.Toast;

import com.jqhee.latte.core.wechat.BaseWXPayEntryActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;


/**
 * @author: wuchao
 * @date: 2017/12/27 23:04
 * @desciption:
 */

public class WXPayEntryTemplate extends BaseWXPayEntryActivity {

    @Override
    protected void onPaySuccess() {
        Toast.makeText(this, "支付成功", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayFail() {
        Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    protected void onPayCancel() {
        Toast.makeText(this, "支付取消", Toast.LENGTH_LONG).show();
        finish();
        overridePendingTransition(0, 0);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }
}
