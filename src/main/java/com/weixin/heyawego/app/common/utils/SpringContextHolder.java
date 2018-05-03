package com.weixin.heyawego.app.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/3/4.
 */
@Service
@Lazy(false)
public class SpringContextHolder implements ApplicationContextAware,DisposableBean {

    private static ApplicationContext applicationContext = null;

    /**
     * @Author : surezs
     * @Description :   实现ApplicationContextAware接口 , 注入Context到静态变量
     * @Param : applicationContext
     * @Return : void
     * @Date : 2018/3/4 15:21
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext ;
    }

    /**
	 * 取得存储在静态变量中的ApplicationContext.
	 */
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}
    
    public static <T> T getBean(String beanName){
        return (T)applicationContext.getBean(beanName);
    }

    public static <T> T getBean(Class<T> requireType){
        return (T)applicationContext.getBean(requireType);
    }

    /**
     * @Author : surezs
     * @Description :  实现DisposableBean接口  , 在Context关闭时清理静态变量
     * @Return : void
     * @Date : 2018/3/4 15:19
     */
    @Override
    public void destroy() throws Exception {
        SpringContextHolder.clearHolder();
    }

    public static void clearHolder(){
        applicationContext = null ;
    }
}
