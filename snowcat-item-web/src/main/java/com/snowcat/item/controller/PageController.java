package com.snowcat.item.controller;

import com.snowcat.item.pojo.Item;
import com.snowcat.pojo.TbItem;
import com.snowcat.pojo.TbItemDesc;
import com.snowcat.service.ItemDescService;
import com.snowcat.service.ItemService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/item")
public class PageController {
    @Autowired(required = false)
    private ItemService itemService;

    @Autowired(required = false)
    private ItemDescService itemDescService;

    @RequestMapping("/{id}")
    public String showItemDetail(@PathVariable Long id, Model model){
        TbItem tbItem = itemService.findTbItemById(id);
        Item item = new Item();
        BeanUtils.copyProperties(tbItem,item);
        model.addAttribute("item",item);
        return "item";
    }

    @RequestMapping("/desc/{id}")
    @ResponseBody
    public String showItemDesc(@PathVariable("id") Long id){
        TbItemDesc tbItemDesc = itemDescService.findItemDescById(id);
        String itemDesc = tbItemDesc.getItemDesc();


        return  itemDesc;
    }

}

