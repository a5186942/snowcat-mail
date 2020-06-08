package com.snowcat.mapper;


import com.snowcat.pojo.ItemCatResult;


import java.util.List;

public interface TbItemCatMapper {

//    @Select(("select id,name,isParent from tbitemcat where    "))
    List<ItemCatResult> showZtree(Long id);
}