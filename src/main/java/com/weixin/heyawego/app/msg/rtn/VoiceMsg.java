package com.weixin.heyawego.app.msg.rtn;

/**
 * @Author : xuchang
 * @Description : 语音消息
 * @Date : 2018/4/28 14:09
 */
public class VoiceMsg extends BaseRtnMsg {

    //语音格式，如amr，speex等
    private String Format;

    //语音消息媒体
    private Voice Voice ;

    public VoiceMsg(){}

    public VoiceMsg(String toUserName,String fromUserName ,String msgType , long createTime){
        super.setToUserName(toUserName);
        super.setFromUserName(fromUserName);
        super.setMsgType(msgType);
        super.setCreateTime(createTime);
    }

    public VoiceMsg(String toUserName,String fromUserName ,String msgType , long createTime , Voice voice){
        super.setToUserName(toUserName);
        super.setFromUserName(fromUserName);
        super.setMsgType(msgType);
        super.setCreateTime(createTime);
        this.Voice = voice ;
    }

    public Voice getVoice() {
        return Voice;
    }

    public void setVoice(Voice voice) {
        this.Voice = voice;
    }

    public String getFormat() {
        return Format;
    }

    public void setFormat(String format) {
        Format = format;
    }

    @Override
    public String toString() {
        return "VoiceMsg{" +
                ", voice='" + Voice + '\'' +
                ", Format='" + Format + '\'' +
                '}';
    }
}
