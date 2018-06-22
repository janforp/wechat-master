package com.janita.wechat.common.result;

/**
 * 与页面交互的dto
 */
public class ResultDto {

    /**
     * 结果编码
     */
    private int code;

    /**
     * 返回说明
     */
    private String message;

    /**
     * 返回数据集
     */
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "ResultDto{" +
                "code='" + code + '\'' +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
