package com.snowcat.order.service.config;

import org.springframework.context.annotation.Configuration;


public class AlipayConfig {
    public static String app_id= "2016103000778325";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key= "MIIEuwIBADANBgkqhkiG9w0BAQEFAASCBKUwggShAgEAAoIBAQDErhIw6dRABJvP4tn/NSd8GSN7iwS/j9FSYcf6MpEW/74RmaPVXlOeQsJ2bBKeONEg3lGPXyQ1/xRKHjywbFmn6AoFRIz+ISf8SzUOsppPGhlb9eJ4G1z37hHU+7aL8UgvNJju7icyyYrgy7hQVQira6M839816JA0oam8Y6fulG7d/fTXNue3bhuf/NotISwsjwtoXBPYIAQ7q3az+e0L4/2of4skbF6Bx0K8fVNGD1JUiAx6KcuL5g+Rj0OM3aRHH8PkVDT8vK5HDLj6ynqdW7tEHK5kniDvF5cb3x4sdHwry75kRTdU3HNODdtnFYbDr9NQ4N/6aLg+vo04WTzZAgMBAAECggEAcowTCivVMye7+3pajnlNssgNU1GnBU5kkf9i8cPrnqJ591QtCAK03pJxIXlV6pKasfLpO4Ak3dHe5j/wBqxEFzdcRq8jEosK97RxhUrd9j30EQwGxv7gyYD9H7cuHRj8eom+nEFV0Q1ydfQQN7EeqH+7BaQ5IvN8sTqKWa8MyjTfrKcWIuC7iqlPIuDAKlo6NRjwG2SRbT1I9clKXtD/1YsC3uC5HFY5BGjSnxEH6cBpRe+E4j8ThSe3WgZlF2AbVSBBBCAnXn85vGE6GIFg0xlcM9XmP4b3krbQDoOZRVUEEFa4X9nBqF/PyqDGIp7ToTLmNdI6U1q773cdYCqNFQKBgQD8D8ranVXvC727JKKvrXJg80eEXkp3TsPJhWq3qInNZDDMo5UEB9m46PLc26OxS/qG1WdZ3+f4Eu8IJ6LZR8SuEL5eiX8GqBmly5g1FseH7ViqzpV/+N8FwRG0ZAcTvvGcaQGfxa/O84OD6Xeh9YLJGoNXuXIHAzhmBtPcepsHFwKBgQDHwMKncUeUQ5clfg5PNJ85HbFGjk5ztlblW6VsmDqoXgEcXMK3C9YrsxGPG2lRNK2goTbTR9ya+lCZBJ9ZytWC4/Xy1+raz33vYWsz4m6j6sw2WjdmnbgR4RkAjHqKKc8HkIx6NOTEtv/g16stvnS5rTwS51SpkC6aK4ByFs5RjwKBgB2LtFl/y8zjcW/4UWGKUTfwDFgFvXDSuLxseR/iD5UVw2qZWiRIz0qPc7hTvpJPt6QKa7zrNAOuJHquOraBZRqKN+ZY85zSI399p+/BJc5XEeejo36Gq42/HdYpS3MTFLsiy9/wijUV8jqKKU3Au33bL74erLEJvZioADl93aItAn9f+T2NNeAuXEtCuE4nwCDAUIPcYoNPFUyq5l2Uz49fVcsy1V64k/dTB7ZoC8OOQMErbEnrOZvE2n/fBVmKyunK+bwx8YvllcvsN+JKC4EdQLLdansz0pOeA4j/un8l4vCVsd3M2Z0Q5twKK5UTZW6vx4wViRAkaCH78OrF5i87AoGBAIA8v+jaaD/aXnTwjD9FfIQEosnJ1hU1hqDyOei0j5YKtn4cxWL+2ZTuJG+q1tIQtnReuwt/8+DHJZrJRV3PtucnK+afuNgMJmJ/sB7zVMZLXWtnb5I+9+BmZEVeM1/ua4iY/xZyC7HQrl41UN5uZD2rJQRQZVH/fIUqlkvjl1tI";
    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key= "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArTlHoEnitipkN8cSUafbY6cJ6R/h93Km2avMw6kB7hk7feb8kIGzLLglAN3qjKoBQNxNVPnT5lCxblaxbAu4NjKI7C7NZnDjOAThxokV6c07T3q0Aws4UInSVdkmFcnfqNII0Rx8YfxHR4h5iwTZcsicObdps6+c2sLoYC54qorHfA9bLBoTKAcY3ttf6JNHoam+MElUVsLFMDvEQNM98w1ImTLAcTV0D4VHwFlgKlliRMeqry34bFiH7x4p7cF5C+JYSircLYD+BX00v5JocgOxdpx2aP+mHBBWIlmMkKK/TX8vzPo+24oMnTl5O5NQXfzr2lqI+1Cs1+JTmkYMYwIDAQAB";
    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url= "http://localhost:8092/order/payNotify";
    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问  (这个路径就是当用户支付成功后自动跳转的访问的url地址)
    public static String return_url= "http://localhost:8092/order/create";
    // 签名方式
    public static String sign_type= "RSA2";
    // 字符编码格式
    public static String charset = "utf-8";
    // 支付宝网关
    public static String gatewayUrl= "https://openapi.alipaydev.com/gateway.do";

//    // 支付宝网关
//    public static String log_path= "C:\\";

}