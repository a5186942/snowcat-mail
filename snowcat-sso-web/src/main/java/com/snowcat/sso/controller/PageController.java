package com.snowcat.sso.controller;

import com.snowcat.sso.service.JedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/page")
public class PageController {



    @RequestMapping("/login")
    public String showLoginPage(){
        return "/login";
    }


    @RequestMapping("/register")
    public String showRegisterPage(){
    return "/register";
    }





}