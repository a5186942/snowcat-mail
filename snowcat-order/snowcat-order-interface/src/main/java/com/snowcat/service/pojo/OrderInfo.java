package com.snowcat.service.pojo;

import com.snowcat.pojo.TbOrder;
import com.snowcat.pojo.TbOrderItem;
import com.snowcat.pojo.TbOrderShipping;

import java.io.Serializable;
import java.util.List;

public class OrderInfo implements Serializable {
    private TbOrder tbOrder;
    private List<TbOrderItem> tbOrderItems;
    private TbOrderShipping tbOrderShipping;

    @Override
    public String toString() {
        return "OrderInfo{" +
                "tbOrder=" + tbOrder +
                ", tbOrderItems=" + tbOrderItems +
                ", tbOrderShipping=" + tbOrderShipping +
                '}';
    }

    public TbOrder getTbOrder() {
        return tbOrder;
    }

    public void setTbOrder(TbOrder tbOrder) {
        this.tbOrder = tbOrder;
    }

    public List<TbOrderItem> getTbOrderItems() {
        return tbOrderItems;
    }

    public void setTbOrderItems(List<TbOrderItem> tbOrderItems) {
        this.tbOrderItems = tbOrderItems;
    }

    public TbOrderShipping getTbOrderShipping() {
        return tbOrderShipping;
    }

    public void setTbOrderShipping(TbOrderShipping tbOrderShipping) {
        this.tbOrderShipping = tbOrderShipping;
    }
}
