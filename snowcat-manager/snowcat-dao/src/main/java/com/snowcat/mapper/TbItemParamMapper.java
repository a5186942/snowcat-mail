package com.snowcat.mapper;


import com.snowcat.pojo.ItemParamGroup;
import com.snowcat.pojo.ItemParamKey;
import com.snowcat.pojo.ItemParamValue;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbItemParamMapper {
    @Select("select * from tbitemparamvalue where itemId = #{id}")
    List<ItemParamValue> getParamById(Long id);

    @Select("select * from tbitemparamkey where id = #{id}")
    ItemParamKey getParamKeyById(Integer id);

    @Select("select * from tbitemparamgroup where id = #{id}")
    ItemParamGroup getParamGroupById(Integer id);

    List<ItemParamGroup> showItemParam(Long id);
   
}