package com.snowcat.service.impl;

import com.snowcat.mapper.TbItemDescMapper;
import com.snowcat.pojo.TbItemDesc;
import com.snowcat.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private TbItemDescMapper tbItemDescMapper;

    @Override
    public int addItemDesc(TbItemDesc tbItemDesc) {
        Date date = new Date();
        tbItemDesc.setCreated(date);
        tbItemDesc.setUpdated(date);
        return tbItemDescMapper.addItemDesc(tbItemDesc);

    }

    @Override
    public TbItemDesc findItemDescById(Long id) {

        return tbItemDescMapper.findItemDescById(id);
    }
}
