package com.jqhee.bfastec.example.generators;

import com.jqhee.latte.annotations.EntryGenerator;
import com.jqhee.latte.core.wechat.templates.WXEntryTemplate;

/**
 * @author: wuchao
 * @date: 2017/12/27 23:05
 * @desciption:
 */
@EntryGenerator(packageName = "com.jqhee.bfastec.example",
        entryTemplate = WXEntryTemplate.class)
public interface WeChatEntry {
}
