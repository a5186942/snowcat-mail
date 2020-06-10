package com.snowcat.service.impl;

import com.snowcat.mapper.TbContentCategoryMapper;
import com.snowcat.mapper.TbContentMapper;
import com.snowcat.pojo.ContentZtreeResult;
import com.snowcat.pojo.ElementResult;
import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.TbContent;
import com.snowcat.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    TbContentMapper tbContentMapper;

    @Override
    public List<ContentZtreeResult> showList(Long id) {

        List<ContentZtreeResult> list  =tbContentCategoryMapper.showList(id);

        return list;
    }

    @Override
    public LayuiResult showTable(Integer page, Integer limit, Long categoryId) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        int count = tbContentMapper.showTableByCount(categoryId);
        layuiResult.setCount(count);
        List<TbContent> list =  tbContentMapper.showTable((page-1)*limit,limit,categoryId);
        layuiResult.setData(list);
        return layuiResult;



    }

    @Override
    public LayuiResult deleteContent(List<Long> id) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setData(null);
        layuiResult.setCount(0);

        if(id==null || id.size()==0){

            layuiResult.setMsg("删除失败");

            return layuiResult;
        }
         tbContentMapper.deleteContent(id);
        layuiResult.setMsg("删除成功");

        return layuiResult;



    }

    @Override
    public ElementResult showElement(Long categoryId) {

        TbContent tbContent = tbContentMapper.showElement(categoryId);
        ElementResult elementResult = new ElementResult();
        elementResult.setAlt(tbContent.getTitle());
        elementResult.setSrc(tbContent.getPic());
        elementResult.setSrcB(tbContent.getPic2());
        elementResult.setHref(tbContent.getUrl());
        return elementResult;
    }

    @Override
    public LayuiResult addContent(TbContent tbContent) {

        if(tbContent==null){
            return LayuiResult.build(500,"增加失败");
        }
        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        int count = tbContentMapper.addContent(tbContent);

        if(count <= 0){
            return LayuiResult.build(500,"添加失败");
        }
        return LayuiResult.build(200,"增加成功");



    }
}
