package com.snowcat.pojo;

import java.io.Serializable;

public class PicResult implements Serializable {
    private Integer code;
    private String msg;
    private PicUrl picUrl;

    @Override
    public String toString() {
        return "PicResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", picUrl=" + picUrl +
                '}';
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public PicUrl getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(PicUrl picUrl) {
        this.picUrl = picUrl;
    }
}
