package com.weixin;

import com.weixin.heyawego.app.common.anotation.MyBatisDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableCaching
@EnableScheduling
@MapperScan(basePackages = "com.weixin.heyawego",annotationClass = MyBatisDao.class)
public class HeyawegoApplication {

	public static ConfigurableApplicationContext context ;

	public static void main(String[] args) {
		context = SpringApplication.run(HeyawegoApplication.class, args);
	}
}
