package com.snowcat.pojo;

import java.io.Serializable;

public class PicUrl implements Serializable {
    private String url;

    @Override
    public String toString() {
        return "PicUrl{" +
                "url='" + url + '\'' +
                '}';
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
