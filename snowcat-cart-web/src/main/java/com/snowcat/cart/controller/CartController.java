package com.snowcat.cart.controller;

import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.TbItem;

import com.snowcat.service.ItemService;
import com.snowcat.utils.CookieUtils;
import com.snowcat.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/add/{itemId}")
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



//    显示购物车商品列表
    @RequestMapping("/cart")
    public String showCartList(HttpServletRequest httpServletRequest, Model model){
        String cartList = CookieUtils.getCookieValue(httpServletRequest, "TT_CART");
        List<TbItem> tbItems = JsonUtils.jsonToList(cartList, TbItem.class);
        model.addAttribute("cartList",tbItems);
        return "cart";
    }



//    修改购物车种商品数量
    @RequestMapping("/update/num/{itemId}/{num}")
    @ResponseBody
    public ExecuteResult updateCartNum(@PathVariable("itemId") Long itemId,@PathVariable("num") Integer num,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){


        String cartList = CookieUtils.getCookieValue(httpServletRequest, "TT_CART");
        List<TbItem> tbItems = JsonUtils.jsonToList(cartList, TbItem.class);



        boolean flag = false;

        if(num<=0){
            for(TbItem item:tbItems){
                if(item.getId()==itemId){
                    tbItems.remove(item);
                    flag=true;
                    break;
                }
            }
            flag=true;
        }else {
            for(TbItem item:tbItems){
                if(item.getId()==itemId){
                    item.setNum(num);
                    flag=true;
                    break;
                }
            }

        }

        if(flag==false){
            return ExecuteResult.build(400,"商品数量修改失败");
        }

        String json = JsonUtils.objectToJson(tbItems);
        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"TT_CART",json);

        return ExecuteResult.build(200,"商品数量修改成功");
    }



    @RequestMapping("/delete/{itemId}")
    public String delCart(@PathVariable("itemId") Long itemId,Model model,HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){


        String cartList = CookieUtils.getCookieValue(httpServletRequest, "TT_CART");
        List<TbItem> tbItems = JsonUtils.jsonToList(cartList, TbItem.class);


        for(TbItem item:tbItems){
            if(item.getId()==itemId){
                tbItems.remove(item);

                break;
            }
        }

        String json = JsonUtils.objectToJson(tbItems);

        CookieUtils.setCookie(httpServletRequest,httpServletResponse,"TT_CART",json);

        return "cart";
    }


}
