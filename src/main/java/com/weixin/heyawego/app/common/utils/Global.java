package com.weixin.heyawego.app.common.utils;

import org.springframework.core.env.Environment;

/**
 * @Author : xuchang
 * @Description :  Global类 用于提取配置文件中的值
 * @Date : 2018/4/27 16:37
 */
public class Global {

    private static Environment environment = SpringContextHolder.getBean("environment");

    public static String getConfig(String key){
        return  environment.getProperty(key);
    }

}
