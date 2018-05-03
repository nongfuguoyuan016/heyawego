package com.weixin.heyawego.app.msg.req;

/**
 * @Author : xuchang
 * @Description :  事件消息
 * @Date : 2018/5/3 14:38
 */
public class EventMsg extends BaseReqMsg {

    //事件类型,必需 (subscribe,unsubscribe,SCAN,CLICK,VIEW,LOCATION)
    private String Event ;

    //事件KEY值，是一个32位无符号整数，即创建二维码时的二维码scene_id
    private String EventKey ;

    //二维码的ticket，可用来换取二维码图片
    private String Ticket ;

    //地理位置纬度
    private String Latitude ;

    //地理位置经度
    private String Longitude ;

    //地理位置精度
    private String Precision;

    public String getEvent() {
        return Event;
    }

    public void setEvent(String event) {
        Event = event;
    }

    public String getEventKey() {
        return EventKey;
    }

    public void setEventKey(String eventKey) {
        EventKey = eventKey;
    }

    public String getTicket() {
        return Ticket;
    }

    public void setTicket(String ticket) {
        Ticket = ticket;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getPrecision() {
        return Precision;
    }

    public void setPrecision(String precision) {
        Precision = precision;
    }

    @Override
    public String toString() {
        return "EventMsg{" +
                "Event='" + Event + '\'' +
                ", EventKey='" + EventKey + '\'' +
                ", Ticket='" + Ticket + '\'' +
                ", Latitude='" + Latitude + '\'' +
                ", Longitude='" + Longitude + '\'' +
                ", Precision='" + Precision + '\'' +
                '}';
    }
}
