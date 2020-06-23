package com.snowcat.mapper;


import com.snowcat.pojo.TbOrderShipping;
import org.apache.ibatis.annotations.Insert;

public interface TbOrderShippingMapper {
    @Insert("insert into tbordershipping (orderId, receiverName, receiverPhone, receiverMobile, receiverState, receiverCity, receiverDistrict, receiverAddress, receiverZip, created, updated) VALUES " +
            "(#{orderId}, #{receiverName},#{receiverPhone}, #{receiverMobile}, #{receiverState}, #{receiverCity}, #{receiverDistrict}, #{receiverAddress}, #{receiverZip}, #{created}, #{updated})")
    int create(TbOrderShipping orderShipping);
}