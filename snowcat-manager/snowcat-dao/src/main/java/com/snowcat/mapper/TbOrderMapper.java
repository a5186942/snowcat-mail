package com.snowcat.mapper;


import com.snowcat.pojo.TbOrder;
import org.apache.ibatis.annotations.Insert;

public interface TbOrderMapper {
    @Insert("insert into tborder (orderId, payment, paymentType, postFee, status, createTime, updateTime, paymentTime, consignTime, endTime, closeTime, shippingName, shippingCode, userId, buyerMessage, buyerNick, buyerRate) " +
            "values (#{orderId}, #{payment}, #{paymentType}, #{postFee}, #{status}, #{createTime}, #{updateTime}, #{paymentTime}, #{consignTime}, #{endTime}, #{closeTime}, #{shippingName}, #{shippingCode}, #{userId}, #{buyerMessage}, #{buyerNick}, #{buyerRate})")
    int create(TbOrder tbOrder);



}