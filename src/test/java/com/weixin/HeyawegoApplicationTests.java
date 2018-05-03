package com.weixin;

import com.weixin.heyawego.app.common.utils.Global;
import com.weixin.heyawego.app.dao.UserDao;
import com.weixin.heyawego.app.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HeyawegoApplicationTests {

	@Autowired
	private UserDao userDao;

	@Test
	public void contextLoads() {
		System.out.println(Global.getConfig("appName"));

	}

	@Test
	public void testDao(){
		User user = userDao.getById("1");
		if( null != user){
			System.out.println(user);
		}else{
			System.out.println("-----> null");
		}
	}
}
