package com.snowcat.order.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import com.snowcat.mapper.TbOrderItemMapper;
import com.snowcat.mapper.TbOrderMapper;
import com.snowcat.mapper.TbOrderShippingMapper;
import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.TbOrder;
import com.snowcat.pojo.TbOrderItem;
import com.snowcat.pojo.TbOrderShipping;
import com.snowcat.service.OrderService;
import com.snowcat.service.pojo.OrderInfo;
import com.snowcat.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import com.snowcat.order.service.config.*;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private TbOrderMapper tbOrderMapper;

    @Autowired
    private TbOrderItemMapper tbOrderItemMapper;

    @Autowired
    private TbOrderShippingMapper tbOrderShippingMapper;


    @Override
    public ExecuteResult createOrdre(OrderInfo orderInfo) {
        long id = IDUtils.genItemId();
        TbOrder order = orderInfo.getTbOrder();
        List<TbOrderItem> orderItems = orderInfo.getTbOrderItems();
        TbOrderShipping orderShipping = orderInfo.getTbOrderShipping();


        order.setOrderId("id");
        for(TbOrderItem tbOrderItem :orderItems){
            tbOrderItem.setOrderId("id");
        }
        orderShipping.setOrderId("id");


        int count1 = tbOrderMapper.create(order);

        int count2 = tbOrderItemMapper.create(orderItems);

        int count3 = tbOrderShippingMapper.create(orderShipping);



        if(count1<=0||count2<=0||count3<=0){
            return ExecuteResult.build(400,"订单生成失败");
        }


        return ExecuteResult.build(200,"订单生成成功");


        
    }

    @Override
    public void Alipay(HttpServletRequest request, HttpServletResponse response) {
        AlipayClient alipayClient = new DefaultAlipayClient(
                AlipayConfig.gatewayUrl,
                AlipayConfig.app_id,
                AlipayConfig.merchant_private_key,
                "json",
                AlipayConfig.charset,
                AlipayConfig.alipay_public_key,
                AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = null;
        try {
            //付款金额，必填
            String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"), "UTF-8");
            //订单名称，必填
            String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"), "UTF-8");
            //商品描述，可空
            String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"), "UTF-8");
            out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"), "UTF-8");
            alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\","
                    + "\"total_amount\":\"" + total_amount + "\","
                    + "\"subject\":\"" + subject + "\","
                    + "\"body\":\"" + body + "\","
                    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

            String form = "";

            form = alipayClient.pageExecute(alipayRequest).getBody(); //调用SDK生成表单

            response.setContentType("text/html;charset=" + AlipayConfig.charset);
            response.getWriter().write(form);//直接将完整的表单html输出到页面
            response.getWriter().flush();
            response.getWriter().close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (AlipayApiException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //    + "\"total_amount\":\""+ total_amount +"\","
        //    + "\"subject\":\""+ subject +"\","
        //    + "\"body\":\""+ body +"\","
        //    + "\"timeout_express\":\"10m\","
        //    + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求

    }


}
