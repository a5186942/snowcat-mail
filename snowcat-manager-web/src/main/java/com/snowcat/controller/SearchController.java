package com.snowcat.controller;

import com.snowcat.pojo.ExecuteResult;
import com.snowcat.search.service.ItemSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private ItemSearchService itemSearchService;

    @RequestMapping("/importSolr")
    @ResponseBody
    public ExecuteResult importSolr(){
        ExecuteResult executeResult = itemSearchService.importSolr();
        return executeResult;
    }
}
