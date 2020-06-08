package com.snowcat.service;

import com.snowcat.pojo.ItemCatResult;
import com.snowcat.pojo.TbContent;
import com.snowcat.pojo.TbItemCat;

import java.util.List;

public interface ItemCatService {
    List<ItemCatResult> showZtree(Long id);

}
