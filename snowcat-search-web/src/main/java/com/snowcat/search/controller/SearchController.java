package com.snowcat.search.controller;

import com.snowcat.pojo.SearchResult;
import com.snowcat.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller

public class SearchController {

    @Autowired
     private ItemSearchService itemSearchService;

    @RequestMapping("/search")
    public String showSearch(@RequestParam("q") String query, @RequestParam(value = "page",defaultValue = "1") Integer page, Model model){
        try {
            String s = new String(query.getBytes("iso-8859-1"), "UTF-8");
            System.out.println(s);
            SearchResult searchResult = itemSearchService.search(s,page);
            model.addAttribute("query",s);
            model.addAttribute("page",page);
            model.addAttribute("itemList",searchResult.getSearchItemResults());
            model.addAttribute("totalPages",searchResult.getTotalPages());
            model.addAttribute("totalCount",searchResult.getTotalCounts());


            return "search";
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
