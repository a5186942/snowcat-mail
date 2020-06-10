package com.snowcat.controller;

import com.snowcat.pojo.ElementResult;
import com.snowcat.service.ContentService;
import com.snowcat.utils.JsonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.xml.ws.Service;

@Controller
public class PageController {
    @Autowired
    ContentService contentService;

    @RequestMapping("/")
    public ModelAndView showPage() {
        ElementResult elementResult = contentService.showElement(89L);
        String result = JsonUtils.objectToJson(elementResult);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("ad1",result);
        return modelAndView;
    }
}
