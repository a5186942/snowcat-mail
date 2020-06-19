package com.snowcat.sso.controller;

import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.TbUser;
import com.snowcat.sso.service.CheckService;
import com.snowcat.utils.CookieUtils;
import com.snowcat.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.jboss.netty.handler.codec.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private CheckService checkService;

    @RequestMapping("/check/{param}/{type}")
    @ResponseBody
    public ExecuteResult checkData(@PathVariable("param") String param,@PathVariable("type") Integer type){

            ExecuteResult executeResult = checkService.checkData(param,type);
            return executeResult;
    }

    @RequestMapping("/register")
    @ResponseBody

    public ExecuteResult CheckLog(TbUser tbUser){

        ExecuteResult executeResult = checkService.register(tbUser);
        return executeResult;

    }

    @RequestMapping("/login")
    @ResponseBody
    public ExecuteResult login(String userName, String passWord, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
            ExecuteResult executeResult = checkService.login(userName,passWord);
            if(executeResult.getData()!=null){
                CookieUtils.setCookie(httpServletRequest,httpServletResponse,"TT_TOKEN",executeResult.getData().toString());
            }


            return executeResult;
    }


    @RequestMapping("/token/{token}")
    @ResponseBody
    public String checkToken(@PathVariable("token") String token,String callback){

        ExecuteResult executeResult = checkService.checkToken(token);
        if(StringUtils.isNotBlank(callback)){
            return callback+"("+JsonUtils.objectToJson(executeResult)+");";
        }

        return JsonUtils.objectToJson(executeResult);

    }

    @RequestMapping("/logout/{token}")
    @ResponseBody

    public String logout(@PathVariable("token") String token,String callback){
        ExecuteResult result = checkService.logout(token);
        if(callback!=null){
            return callback+"("+JsonUtils.objectToJson(result)+");";
        }
        return JsonUtils.objectToJson(result);
    }


}
