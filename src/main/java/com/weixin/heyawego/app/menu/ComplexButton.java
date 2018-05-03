package com.weixin.heyawego.app.menu;

/**
 * @Author : xuchang
 * @Description : 父菜单项 :包含有二级菜单项的一级菜单。这类菜单项包含有二个属性：name和sub_button，而sub_button以是一个子菜单项数组
 * @Date : 2018/5/2 16:06
 */
public class ComplexButton extends Button{

    //二级菜单数组，个数应为1~5个
    private Button[] sub_button ;

    public Button[] getSub_button() {
        return sub_button;
    }

    public void setSub_button(Button[] sub_button) {
        this.sub_button = sub_button;
    }
}
