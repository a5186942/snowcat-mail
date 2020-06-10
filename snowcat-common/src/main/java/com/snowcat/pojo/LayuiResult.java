package com.snowcat.pojo;

import java.io.Serializable;
import java.util.List;

public class LayuiResult implements Serializable {
    private int code;
    private String msg;
    private int count;
    List<?> data;


    public static LayuiResult build(int code,String msg){
       LayuiResult layuiResult =  new LayuiResult(code,msg);
       layuiResult.setCount(0);
       layuiResult.setData(null);
       return layuiResult;
    }

    public LayuiResult() {
    }


    public LayuiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "LayuiResult{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", count=" + count +
                ", data=" + data +
                '}';
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
