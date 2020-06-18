package com.snowcat.controller;

import com.snowcat.pojo.ElementResult;
import com.snowcat.pojo.TbContent;
import com.snowcat.service.ContentService;
import com.snowcat.utils.JsonUtils;
import org.apache.zookeeper.server.SessionTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.jws.WebParam;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {
    @Autowired(required = false)
    ContentService contentService;

    @RequestMapping("/" )
    public String showPage(Model model) {
        List<TbContent> list = contentService.showElement(89L);

        ArrayList<ElementResult> elementResults = new ArrayList<>();
        for(TbContent item:list){
            ElementResult elementResult = new ElementResult(item.getPic2(), item.getTitle(), item.getPic(), item.getUrl());
            elementResults.add(elementResult);

        }
        String result = JsonUtils.objectToJson(elementResults);
        model.addAttribute("ad1",result);





        return "index";
    }
}
