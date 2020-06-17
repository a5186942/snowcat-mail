package com.snowcat.pojo;

import java.io.Serializable;
import java.util.List;

public class ItemParamGroup implements Serializable {
    private Integer id;
    private String groupName;
    private Long itemCatId;
    private List<ItemParamKey> paramKeyList;

    @Override
    public String toString() {
        return "ItemParamGroup{" +
                "id=" + id +
                ", groupName='" + groupName + '\'' +
                ", itemCatId=" + itemCatId +
                ", paramKeyList=" + paramKeyList +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Long getItemCatId() {
        return itemCatId;
    }

    public void setItemCatId(Long itemCatId) {
        this.itemCatId = itemCatId;
    }

    public List<ItemParamKey> getParamKeyList() {
        return paramKeyList;
    }

    public void setParamKeyList(List<ItemParamKey> paramKeyList) {
        this.paramKeyList = paramKeyList;
    }
}
