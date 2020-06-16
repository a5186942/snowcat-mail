package com.snowcat.item.pojo;


import com.snowcat.pojo.TbItem;

public class Item extends TbItem {
    public Item(TbItem tbItem) {
        this.setBarcode(tbItem.getBarcode());
        this.setcId(tbItem.getcId());
        this.setCreated(tbItem.getCreated());
        this.setId(tbItem.getId());
        this.setImage(tbItem.getImage());
        this.setNum(tbItem.getNum());
        this.setPrice(tbItem.getPrice());
        this.setSellPoint(tbItem.getSellPoint());
        this.setStatus(tbItem.getStatus());
        this.setTitle(tbItem.getTitle());
        this.setUpdated(tbItem.getUpdated());
    }
    public Item() {

    }
    public String[] getImages() {
        String image2 = this.getImage();
        if (image2 != null && !"".equals(image2)) {
            String[] strings = image2.split(",");
            return strings;
        }
        return null;
    }


}
