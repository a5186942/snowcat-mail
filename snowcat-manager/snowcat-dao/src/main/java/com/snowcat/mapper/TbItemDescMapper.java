package com.snowcat.mapper;


import com.snowcat.pojo.TbItemDesc;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.Date;

public interface TbItemDescMapper {
    @Insert(" insert into tbitemdesc(itemId,itemDesc,created,updated) values (#{itemId},#{itemDesc},#{created},#{updated})" )
    int addItemDesc(TbItemDesc tbItemDesc);
    @Select(("select * from tbitemdesc where itemId = #{id}"))
    TbItemDesc findItemDescById(Long id);
}