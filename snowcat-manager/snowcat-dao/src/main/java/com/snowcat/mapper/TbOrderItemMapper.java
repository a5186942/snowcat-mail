package com.snowcat.mapper;

import com.snowcat.pojo.TbOrderItem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbOrderItemMapper {

    int create(@Param("orderItems") List<TbOrderItem> orderItems);
}