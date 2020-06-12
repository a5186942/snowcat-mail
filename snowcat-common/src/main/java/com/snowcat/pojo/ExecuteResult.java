package com.snowcat.pojo;

import java.io.Serializable;

public class ExecuteResult implements Serializable {
    private Integer status;
    private String msg;
    private Object data;

    public static ExecuteResult Ok(){
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        executeResult.setStatus(0);
        executeResult.setMsg("");
        return executeResult;
    }

    public ExecuteResult() {
    }

    public ExecuteResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeletetResult{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
    public static ExecuteResult build(Integer status,String msg){
            return new ExecuteResult(status,msg,null);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
