package com.snowcat.mapper;


import com.snowcat.pojo.TbItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemMapper {

    @Select("select * from tbitem where id = #{id}")
    TbItem findTbItemById(Long itemId);
    @Select("select count(*) from tbitem")
    int findTbItemByCount();

    @Select("select * from tbitem limit #{index},#{pageSize} ")
    List<TbItem> findTbItemByPage(@Param("index") int index, @Param("pageSize") int pageSize);
}