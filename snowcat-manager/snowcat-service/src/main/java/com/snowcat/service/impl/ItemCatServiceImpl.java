package com.snowcat.service.impl;

import com.snowcat.mapper.TbItemCatMapper;
import com.snowcat.pojo.ItemCatResult;
import com.snowcat.pojo.TbItemCat;
import com.snowcat.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    TbItemCatMapper tbItemCatMapper;
    @Override
    public List<ItemCatResult> showZtree(Long id) {

        List<ItemCatResult> list = tbItemCatMapper.showZtree(id);

        return list;

    }
}
