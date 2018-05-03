package com.weixin.heyawego.app.service;

import com.weixin.heyawego.app.dao.UserDao;
import com.weixin.heyawego.app.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2018/4/27.
 */
@Service
public class UserService {

    @Autowired
    private UserDao userDao ;

    @Cacheable
    public User getById(String id){
        User user =  userDao.getById(id);
        System.out.println("user1 : " + user);
        System.out.println("set user1 name  : 测试");
        user.setUserName("测试");
        User user2 = userDao.getById(id);
        System.out.println("user2 : "+user2);
        return user ;
    }


    @Cacheable(value = "user" ,key = "#message")
    public String testEhCache(String message){
        System.out.println(" print message : "+message);
        return "u send message is : " + message ;
    }
}
