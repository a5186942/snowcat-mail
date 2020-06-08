package com.snowcat.controller;

import com.snowcat.pojo.ItemCatResult;
import com.snowcat.pojo.TbItemCat;
import com.snowcat.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/itemCat")


    public class ItemCatController {
    @Autowired
    ItemCatService itemCatService;

        @RequestMapping("/showZtree")
        @ResponseBody()
        public List<ItemCatResult> showZtree(@RequestParam(defaultValue = "0",value = "id")Long id){

            List<ItemCatResult> list  = itemCatService.showZtree(id);

            return list;
        }

}
