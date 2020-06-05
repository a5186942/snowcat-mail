package com.snowcat.service;

import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.TbItem;

public interface ItemService {
    TbItem findTbItemById(Long itemId);
    LayuiResult findTbItemByPage(int page,int limit);
}
