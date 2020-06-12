package com.snowcat.controller;

import com.snowcat.pojo.ContentZtreeResult;
import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.TbContent;
import com.snowcat.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/content")
public class ContentController {
    @Autowired
    ContentService contentService;
//content/showContentZtree

    @RequestMapping("/showContentZtree")
    @ResponseBody
    public List<ContentZtreeResult> showList(@RequestParam(defaultValue = "0",value = "id") Long id){
        List<ContentZtreeResult> list  = contentService.showList(id);
        return list;
    }

    @RequestMapping("/showContentTable")
    @ResponseBody
    public LayuiResult showTable(Integer page,Integer limit ,Long categoryId){
        LayuiResult layuiResult = contentService.showTable(page,limit,categoryId);
        return layuiResult;
    }


    @RequestMapping("/deleteContentByCategoryId")
    @ResponseBody
    public  LayuiResult deleteContent(@RequestBody List<TbContent> tbContents){
//        List<Long> ids = tbContents.stream().filter((item)->{
//            return item.getId();
//        }).collect(Collectors.toCollection());
        ArrayList<Long> list = new ArrayList<>();

        for(TbContent tbContent:tbContents){
            list.add(tbContent.getId());
        }

        LayuiResult layuiResult = contentService.deleteContent(list);
        return layuiResult;
    }

    @RequestMapping("/addContent")
    @ResponseBody

    public LayuiResult addContent(TbContent tbContent){
        LayuiResult layuiResult = contentService.addContent(tbContent);
        return layuiResult;
    }
}
