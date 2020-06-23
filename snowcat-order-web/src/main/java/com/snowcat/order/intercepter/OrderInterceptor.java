package com.snowcat.order.intercepter;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class OrderInterceptor implements HandlerInterceptor {





    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        JedisPool jedisPool = new JedisPool("127.168.17.100",6379);
        Jedis resource = jedisPool.getResource();


        Cookie[] cookies = httpServletRequest.getCookies();
        String value=null;
        for(Cookie cookie:cookies){
            if(cookie.getName()=="TT_TOKEN"){
                 value = cookie.getValue();
                 break;
            }
        }


        if(StringUtils.isEmpty(value)||StringUtils.isBlank(value)){
            httpServletResponse.sendRedirect("localhost:8088/user/login");
        }

        String user = resource.get(value);

        if(StringUtils.isEmpty(user)||StringUtils.isBlank(user)){
            httpServletResponse.sendRedirect("localhost:8088/user/login");
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
