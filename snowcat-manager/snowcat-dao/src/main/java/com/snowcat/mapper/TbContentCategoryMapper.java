package com.snowcat.mapper;


import com.snowcat.pojo.ContentZtreeResult;
import com.snowcat.pojo.TbContentCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TbContentCategoryMapper {
    @Select("select * from tbcontentcategory where parentId = #{id}")
    List<ContentZtreeResult> showList(Long id);
}