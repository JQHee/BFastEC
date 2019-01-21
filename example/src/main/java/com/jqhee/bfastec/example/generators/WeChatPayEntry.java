package com.jqhee.bfastec.example.generators;


import com.jqhee.latte.annotations.PayEntryGenerator;
import com.jqhee.latte.core.wechat.templates.WXPayEntryTemplate;

/**
 * @author: wuchao
 * @date: 2017/12/27 23:05
 * @desciption:
 */
@PayEntryGenerator(packageName = "com.jqhee.bfastec.example",
        payEntryTemplate = WXPayEntryTemplate.class)
public interface WeChatPayEntry {
}
