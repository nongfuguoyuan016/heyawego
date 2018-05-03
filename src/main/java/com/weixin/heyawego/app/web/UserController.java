package com.weixin.heyawego.app.web;

import com.weixin.heyawego.app.entity.User;
import com.weixin.heyawego.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by admin on 2018/4/27.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService ;

    @RequestMapping(value = "/user")
    public User getUser(String id){
        return userService.getById(id) ;
    }

    @RequestMapping("/eh")
    public String ehCacheTest(String msg){
        return userService.testEhCache(msg) ;
    }

}
