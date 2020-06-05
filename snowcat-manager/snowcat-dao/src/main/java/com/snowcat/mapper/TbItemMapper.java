package com.snowcat.mapper;


import com.snowcat.pojo.TbItem;
import org.apache.ibatis.annotations.Select;

public interface TbItemMapper {

    @Select("select * from tbitem where id = #{id}")
    TbItem findTbItemById(Long itemId);
}