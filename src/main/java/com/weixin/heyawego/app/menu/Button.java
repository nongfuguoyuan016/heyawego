package com.weixin.heyawego.app.menu;

/**
 * @Author : xuchang
 * @Description :  微信一级菜单
 * @Date : 2018/5/2 16:04
 */
public class Button {

    //菜单标题，不超过16个字节，子菜单不超过60个字节 , 所有菜单共有的属性
    private String name ;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
