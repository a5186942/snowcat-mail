package com.snowcat.service.impl;

import com.snowcat.mapper.TbItemMapper;
import com.snowcat.pojo.*;
import com.snowcat.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(Long itemId) {

        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }

    @Override
    public LayuiResult findTbItemByPage(int page, int limit) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        int count = tbItemMapper.findTbItemByCount();
        layuiResult.setCount(count);
        List<TbItem> data = tbItemMapper.findTbItemByPage((page-1)*limit,limit);
        layuiResult.setData(data);
        return layuiResult;

    }

    @Override
    public ExecuteResult itemDelete(List<TbItem> tbItem) {
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        int status = 3;
        int count  =  tbItemMapper.change(tbItem,status);

        if(count!=tbItem.size()||tbItem.size()==0){
            executeResult.setStatus(400);
            executeResult.setMsg("删除失败");
        }else{
            executeResult.setMsg("删除成功");
            executeResult.setStatus(200);

        }

        return executeResult;

    }

    @Override
    public ExecuteResult commitUpper(List<TbItem> tbItem) {
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        int status = 1;
        int count  =  tbItemMapper.change(tbItem,status);

        if(count!=tbItem.size()||tbItem.size()==0){
            executeResult.setStatus(400);
            executeResult.setMsg("上架失败");
        }else{
            executeResult.setMsg("上架成功");
            executeResult.setStatus(200);

        }

        return executeResult;


    }

    @Override
    public ExecuteResult commitLower(List<TbItem> tbItem) {

        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        int status = 2;
        int count  =  tbItemMapper.change(tbItem,status);

        if(count!=tbItem.size()||tbItem.size()==0){
            executeResult.setStatus(400);
            executeResult.setMsg("下架失败");
        }else{
            executeResult.setMsg("下架成功");
            executeResult.setStatus(200);

        }

        return executeResult;

    }

    @Override
    public LayuiResult searchItem(Integer page,Integer limit,String title , Long minPrice,Long maxPrice,Long cid) {
        LayuiResult layuiResult = new LayuiResult();
        if (minPrice == null) {
            minPrice = 0L;
        }

        List<TbItem> list = tbItemMapper.searchItem((page-1)*limit,limit,title, minPrice, maxPrice, cid);
        int count = tbItemMapper.searchItemByCount(title, minPrice, maxPrice, cid);

        if (list != null) {
            int size = list.size();
            if (size <= 0) {
                layuiResult.setCode(400);
                layuiResult.setMsg("查询不到商品");
                layuiResult.setCount(0);
                return layuiResult;
            }


            layuiResult.setData(list);


            layuiResult.setCode(200);
            layuiResult.setMsg("查询成功");
            layuiResult.setCount(count);
        }




        return layuiResult;
    }


}
