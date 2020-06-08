package com.snowcat.controller;

import com.snowcat.pojo.*;
import com.snowcat.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem findTbItem(@PathVariable Long itemId){
        TbItem tbItem = itemService.findTbItemById(itemId);
        return tbItem;
    }
    @RequestMapping("/showItemPage")
    @ResponseBody
    public LayuiResult showItemPage(Integer page,Integer limit){
        LayuiResult result = itemService.findTbItemByPage(page, limit);
        return result;
    }
    @RequestMapping("/itemDelete")
    @ResponseBody
    public ExecuteResult itemDelete(@RequestBody List<TbItem> tbItem){
        ExecuteResult executeResult = itemService.itemDelete(tbItem);
        return executeResult;
    }

    @RequestMapping("/commodityUpperShelves")
    @ResponseBody
    public ExecuteResult commitUpper(@RequestBody List<TbItem> tbItem){
        ExecuteResult executeResult;
        executeResult = itemService.commitUpper(tbItem);
        return  executeResult;
    }

    @RequestMapping("/commodityLowerShelves")
    @ResponseBody
    public ExecuteResult commitLower(@RequestBody List<TbItem> tbItem){
        ExecuteResult executeResult = itemService.commitLower(tbItem);
        return  executeResult;
    }

    @RequestMapping("/searchItem")
    @ResponseBody
    public LayuiResult searchUItem(Integer page,Integer limit,String title , Long minPrice,Long maxPrice,Long cid){
        LayuiResult layuiResult = itemService.searchItem(page,limit,title,minPrice,maxPrice,cid);
        return layuiResult;
    }

}
