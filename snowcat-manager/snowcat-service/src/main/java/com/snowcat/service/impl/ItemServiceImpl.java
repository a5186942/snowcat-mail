package com.snowcat.service.impl;

import com.snowcat.mapper.TbItemMapper;
import com.snowcat.pojo.TbItem;
import com.snowcat.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(Long itemId) {

        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }
}
