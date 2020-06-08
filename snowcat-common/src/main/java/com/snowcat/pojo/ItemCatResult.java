package com.snowcat.pojo;

import java.io.Serializable;

public class ItemCatResult implements Serializable {
    private Long id;
    private String name;
    private Boolean isParent;

    @Override
    public String toString() {
        return "ItemCatResult{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isParent=" + isParent +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getIsParent() {
        return isParent;
    }

    public void setIsParent(Boolean parent) {
        isParent = parent;
    }
}
