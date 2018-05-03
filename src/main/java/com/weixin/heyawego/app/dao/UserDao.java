package com.weixin.heyawego.app.dao;

import com.weixin.heyawego.app.common.anotation.MyBatisDao;
import com.weixin.heyawego.app.entity.User;

@MyBatisDao
public interface UserDao {

    User getById(String id);
}
