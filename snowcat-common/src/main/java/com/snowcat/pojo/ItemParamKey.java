package com.snowcat.pojo;

import java.io.Serializable;

public class ItemParamKey implements Serializable {
    private Integer id;
    private String paramName;
    private Integer groupId;
    private ItemParamValue itemParamValue;

    @Override
    public String toString() {
        return "ItemParamKey{" +
                "id=" + id +
                ", paramName='" + paramName + '\'' +
                ", groupId=" + groupId +
                ", itemParamValue=" + itemParamValue +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public ItemParamValue getItemParamValue() {
        return itemParamValue;
    }

    public void setItemParamValue(ItemParamValue itemParamValue) {
        this.itemParamValue = itemParamValue;
    }
}
