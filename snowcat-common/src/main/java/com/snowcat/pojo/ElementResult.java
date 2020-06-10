package com.snowcat.pojo;

import java.io.Serializable;

public class ElementResult implements Serializable {
    private String srcB;
    private Integer height = 240;
    private String alt;
    private Integer width = 670;
    private String src;
    private Integer widthB = 550;
    private String href;
    private Integer heightB = 240;

    @Override
    public String toString() {
        return "ElementResult{" +
                "srcB='" + srcB + '\'' +
                ", height=" + height +
                ", alt='" + alt + '\'' +
                ", width=" + width +
                ", src='" + src + '\'' +
                ", widthB=" + widthB +
                ", href='" + href + '\'' +
                ", heightB=" + heightB +
                '}';
    }

    public String getSrcB() {
        return srcB;
    }

    public void setSrcB(String srcB) {
        this.srcB = srcB;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getAlt() {
        return alt;
    }

    public void setAlt(String alt) {
        this.alt = alt;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getWidthB() {
        return widthB;
    }

    public void setWidthB(Integer widthB) {
        this.widthB = widthB;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public Integer getHeightB() {
        return heightB;
    }

    public void setHeightB(Integer heightB) {
        this.heightB = heightB;
    }
}
