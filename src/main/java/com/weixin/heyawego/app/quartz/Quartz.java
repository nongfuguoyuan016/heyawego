package com.weixin.heyawego.app.quartz;

import com.weixin.heyawego.app.common.utils.CacheUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Quartz {

    @Scheduled( cron = "0 0 0/2 * * ? ")
    public void flushAccessToken(){
        System.out.println("-----------> 清空accessToken 开始");
        CacheUtils.remove("accessToken");
        CacheUtils.remove("accessTime");
        System.out.println("-----------> 清空accessToken 完成");
    }

}
