package com.weixin.heyawego.app.msg.req;

import com.weixin.heyawego.app.msg.rtn.BaseRtnMsg;

/**
 * @Author : xuchang
 * @Description : 用户发送消息基类
 * @Date : 2018/4/28 14:37
 */
public class BaseReqMsg extends BaseRtnMsg {

    //消息id , 64位整型
    private int MsgId ;

    public int getMsgId() {
        return MsgId;
    }

    public void setMsgId(int msgId) {
        MsgId = msgId;
    }

    @Override
    public String toString() {
        return "BaseReqMsg{" +
                "MsgId=" + MsgId +
                '}';
    }
}
