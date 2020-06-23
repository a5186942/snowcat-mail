package com.snowcat.order.controller;

import com.snowcat.pojo.ElementResult;
import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.TbItem;
import com.snowcat.service.OrderService;
import com.snowcat.service.pojo.OrderInfo;
import com.snowcat.utils.CookieUtils;
import com.snowcat.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @RequestMapping("/order-cart")
    public String showOrderPage(HttpServletResponse response, HttpServletRequest request, Model model) {
        String cartList = CookieUtils.getCookieValue(request, "TT_CART");
        if (StringUtils.isBlank(cartList) || StringUtils.isEmpty(cartList)) {
            model.addAttribute("cartList", new ArrayList<>());
        }
        List<TbItem> tbItems = JsonUtils.jsonToList(cartList, TbItem.class);

        model.addAttribute("cartList", tbItems);
        return "order-cart";
    }


    @RequestMapping("/create")
    @ResponseBody
    public ExecuteResult CreateOrder(OrderInfo orderInfo, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ExecuteResult result = orderService.createOrdre(orderInfo);

        orderService.Alipay(httpServletRequest, httpServletResponse);

        return result;
    }
}





