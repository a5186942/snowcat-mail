package com.snowcat.pojo;

public class ZTreeResult {
    private Integer id;
    private String name;
    private boolean isParent;

    @Override
    public String toString() {
        return "zTreeResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isParent() {
        return isParent;
    }

    public void setParent(boolean parent) {
        isParent = parent;
    }
}
