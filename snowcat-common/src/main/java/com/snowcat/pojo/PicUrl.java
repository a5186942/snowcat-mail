package com.snowcat.pojo;

import java.io.Serializable;

public class PicUrl implements Serializable {
    private String src;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    @Override
    public String toString() {
        return "PicUrl{" +
                "src='" + src + '\'' +
                '}';
    }
}
