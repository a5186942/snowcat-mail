package com.snowcat.mapper;


import com.snowcat.pojo.TbContent;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentMapper {

    @Select("select count(*) from tbcontent where categoryId = #{categoryId}")
    int showTableByCount(Long categoryId);

    @Select("select * from tbcontent where categoryId = #{categoryId} limit #{index},#{limit}")
    List<TbContent> showTable(@Param("index") int index, @Param("limit") Integer limit, @Param("categoryId") Long categoryId);

    int deleteContents(@Param("ids") List<Long> ids);

    @Insert("insert into tbcontent (title,titleDesc,categoryId,subTitle,url,pic,pic2,updated,created) values (#{title},#{titleDesc},#{categoryId},#{subTitle},#{url},#{pic},#{pic2},#{updated},#{created})")
    int addContent(TbContent tbContent);

    List<TbContent> showElement(Long categoryId);
}