package com.snowcat.item.service;

import com.snowcat.pojo.ItemParamGroup;
import com.snowcat.pojo.ItemParamKey;
import com.snowcat.pojo.ItemParamValue;

import java.util.HashSet;
import java.util.List;


public interface ItemParamService {

//    HashSet<ItemParamGroup> showItemParam(Long id);

    List<ItemParamValue> getParamById(Long id);

    ItemParamKey getParamKeyById(Integer id);

     ItemParamGroup getParamGroupById(Integer id);

     List<ItemParamGroup> showItemParam(Long id);
}
