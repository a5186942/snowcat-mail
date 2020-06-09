package com.snowcat.mapper;


import com.snowcat.pojo.TbItem;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

public interface TbItemMapper {

    @Select("select * from tbitem where id = #{itemId}")
    TbItem findTbItemById(Long itemId);
    @Select("select count(*) from tbitem")
    int findTbItemByCount();

    @Select("select * from tbitem limit #{index},#{pageSize} ")
    List<TbItem> findTbItemByPage(@Param("index") int index, @Param("pageSize") int pageSize);

//    @Update("update tbitem set status = 3 where id = #{id}")
//    int deleteById(@Param("id") Long id);

    int change(@Param("tbItem") List<TbItem> tbItem, int status, Date date);

//    int commitUpper(@Param("tbItem") List<TbItem> tbItem);
//
//    int commitLower(@Param("tbItem") List<TbItem> tbItem);

    List<TbItem> searchItem(@Param("index") Integer index ,@Param("limit") Integer limt,@Param("title") String title , @Param("priceMin") Long pirceMin,@Param("priceMax") Long priceMax,@Param("cId") Long cid);

    int searchItemByCount(@Param("title") String title , @Param("priceMin") Long priceMin,@Param("priceMax") Long priceMax,@Param("cId") Long cId);


    int addItem(TbItem tbItem);
}