package com.weixin.heyawego.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

/**
 * Created by admin on 2018/4/27.
 */
@Configuration
@Configurable
@PropertySource(value = "classpath:app.properties",encoding = "UTF-8",ignoreResourceNotFound = true)
public class PropConfig {

    /**
     * @Author : xuchang
     * @Description : 这个bean主要用于解决@value中使用的${…}占位符。假如你不使用${…}占位符的话，可以不使用这个bean。
     * @Return : org.springframework.context.support.PropertySourcesPlaceholderConfigurer
     * @Date : 2018/4/27 16:31
     */
    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
        return new PropertySourcesPlaceholderConfigurer();
    }
}
