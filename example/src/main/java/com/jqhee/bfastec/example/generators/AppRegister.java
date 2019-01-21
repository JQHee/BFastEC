package com.jqhee.bfastec.example.generators;

import com.jqhee.latte.annotations.AppRegisterGenerator;
import com.jqhee.latte.core.wechat.templates.AppRegisterTemplate;

/**
 * @author: wuchao
 * @date: 2017/12/27 23:05
 * @desciption:
 */
@AppRegisterGenerator(packageName = "com.jqhee.bfastec.example",
        registerTemplate = AppRegisterTemplate.class)
public interface AppRegister {
}
