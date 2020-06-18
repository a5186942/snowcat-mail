package com.snowcat.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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