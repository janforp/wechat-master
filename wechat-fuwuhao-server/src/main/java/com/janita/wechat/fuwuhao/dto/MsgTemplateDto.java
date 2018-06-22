package com.janita.wechat.fuwuhao.dto;

import java.util.List;

/**
 * Created on 2018/6/22
 *
 * @author Janita
 */

public class MsgTemplateDto {

    private String msgType;

    private List<String> phoneList;

    private Object data;

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
