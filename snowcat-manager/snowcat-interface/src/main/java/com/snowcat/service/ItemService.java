package com.snowcat.service;

import com.snowcat.pojo.*;

import java.util.List;

public interface ItemService {
    TbItem findTbItemById(Long itemId);
    LayuiResult findTbItemByPage(int page,int limit);


    ExecuteResult itemDelete(List<TbItem> tbItem);

    ExecuteResult commitUpper(List<TbItem> tbItem);

    ExecuteResult commitLower(List<TbItem> tbItem);

    LayuiResult searchItem(Integer page,Integer limit ,String title , Long minPrice,Long maxPrice,Long cid);


    PicResult fileUpload(String fileNanme,byte[] bytes);
}
