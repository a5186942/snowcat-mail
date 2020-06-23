package com.snowcat.service;

import com.snowcat.pojo.ExecuteResult;
import com.snowcat.service.pojo.OrderInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface OrderService {
    ExecuteResult createOrdre(OrderInfo orderInfo);

    void Alipay(HttpServletRequest request, HttpServletResponse response);
}
