package com.snowcat.cart.controller;

import com.snowcat.pojo.TbItem;

import com.snowcat.service.ItemService;
import com.snowcat.utils.CookieUtils;
import com.snowcat.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller

public class PageController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/cart/add/{itemId}")
    public String addCart(@PathVariable("itemId") Long itemId, @RequestParam(value = "num",defaultValue = "1") Integer num, HttpServletResponse httpServletResponse, HttpServletRequest httpServletRequest){

//        查询COOKIE中的商品表
        String value = CookieUtils.getCookieValue(httpServletRequest, "TT_CART");
        List<TbItem> tbItems = JsonUtils.jsonToList(value, TbItem.class);




//        如果集合为空，查询商品信息并添加到集合中
        if(tbItems==null){
            TbItem item = itemService.findTbItemById(itemId);
            tbItems = new ArrayList<>();
            item.setNum(num);
            tbItems.add(item);

        }
        if(tbItems.size()==0){
            TbItem item = itemService.findTbItemById(itemId);
            item.setNum(num);
            tbItems.add(item);
        }


//        判断是否集合中已经有相关商品
        boolean hasItem = false;
        for(TbItem tbItem:tbItems){
            if(tbItem.getId()==itemId){
                tbItem.setNum(tbItem.getNum()+num);
                hasItem=true;
            }
        }


//        集合中没有商品，添加商品

        if(hasItem==false){
            TbItem item = itemService.findTbItemById(itemId);
            item.setNum(num);
            tbItems.add(item);
        }


        String json = JsonUtils.objectToJson(tbItems);

        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"TT_CART",json,604800);



        return "cartSuccess";


    }

}
