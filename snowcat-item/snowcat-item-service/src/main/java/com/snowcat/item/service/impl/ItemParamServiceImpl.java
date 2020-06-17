package com.snowcat.item.service.impl;

import com.snowcat.item.service.ItemParamService;
import com.snowcat.mapper.TbItemParamItemMapper;
import com.snowcat.mapper.TbItemParamMapper;
import com.snowcat.pojo.ItemParamGroup;
import com.snowcat.pojo.ItemParamKey;
import com.snowcat.pojo.ItemParamValue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper tbItemParamMapper;

//    @Override
//    public HashSet<ItemParamGroup> showItemParam(Long id) {
//        List<ItemParamValue> params = tbItemParamItemMapper.getParamById(id);
//        List<ItemParamKey> paramKeys = new ArrayList();
//        for(ItemParamValue item: params){
//            ItemParamKey key = tbItemParamItemMapper.getParamKeyById(item.getParamId());
//            key.setItemParamValue(item);
//            paramKeys.add(key);
//        }
//        HashSet<ItemParamGroup> paramGroups = new HashSet<>();
//        for(ItemParamKey item :paramKeys){
//            ItemParamGroup group = tbItemParamItemMapper.getParamGroupById(item.getGroupId());
//            group.setItemParamKey(item);
//            paramGroups.add(group);
//        }
//        return paramGroups;
//
//
//    }



    @Override
    public List<ItemParamValue> getParamById(Long id) {
        List<ItemParamValue> list = tbItemParamMapper.getParamById(id);
        return list;

    }

    public ItemParamKey getParamKeyById(Integer id){
        return tbItemParamMapper.getParamKeyById(id);
    }

    public ItemParamGroup getParamGroupById(Integer id){
        return tbItemParamMapper.getParamGroupById(id);
    }

    @Override
    public List<ItemParamGroup> showItemParam(Long id) {
        List<ItemParamGroup> itemParamGroups = tbItemParamMapper.showItemParam(id);
        return itemParamGroups;

    }
}
