package com.snowcat.service.impl;

import com.snowcat.mapper.TbContentCategoryMapper;
import com.snowcat.mapper.TbContentMapper;
import com.snowcat.pojo.ContentZtreeResult;
import com.snowcat.pojo.ElementResult;
import com.snowcat.pojo.LayuiResult;
import com.snowcat.pojo.TbContent;
import com.snowcat.service.ContentService;
import com.snowcat.service.JedisClient;
import com.snowcat.utils.JsonUtils;
import com.snowcat.utils.RandomNum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    TbContentMapper tbContentMapper;

    @Autowired
    JedisClient jedisClient;



    @Override
    public List<ContentZtreeResult> showList(Long id) {

        List<ContentZtreeResult> list  =tbContentCategoryMapper.showList(id);

        return list;
    }

    @Override
    public LayuiResult showTable(Integer page, Integer limit, Long categoryId) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        int count = tbContentMapper.showTableByCount(categoryId);
        layuiResult.setCount(count);
        List<TbContent> list =  tbContentMapper.showTable((page-1)*limit,limit,categoryId);
        layuiResult.setData(list);
        return layuiResult;



    }

    @Override
    public LayuiResult deleteContent(List<Long> ids) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setData(null);
        layuiResult.setCount(0);

        if(ids==null || ids.size()==0){

            layuiResult.setMsg("删除失败");

            return layuiResult;
        }
        int count = tbContentMapper.deleteContents(ids);
        if(count<=0){
            layuiResult.setMsg("删除成功");
            return layuiResult;
        }
        layuiResult.setMsg("删除成功");

        //删除缓存中的数据
        jedisClient.del(ids.toString());

        return layuiResult;



    }

    @Override
    public List<TbContent> showElement(Long categoryId) {

        String key = jedisClient.get(categoryId + "");
        if(key!=null&&key!=""){
            List<TbContent> list = JsonUtils.jsonToList(key, TbContent.class);
//            System.out.println("查询缓存");

            return list;


        }
        List<TbContent> list = tbContentMapper.showElement(categoryId);
//        System.out.println("查询数据库");

        //如果数据库查询不到数据
        if(list==null||list.size()==0){
            jedisClient.set(categoryId+"C"," '空'");
            jedisClient.expire(categoryId+"C", RandomNum.randomNum()*60*60);
//            System.out.println("未查询到数据，设置数据为空");

        }else {
            jedisClient.set(categoryId + "C", JsonUtils.objectToJson(list));
            jedisClient.expire(categoryId+"C",RandomNum.randomNum()*60*60);
//            System.out.println("查询到数据，设置到缓存中");
        }

        System.out.println(key);
        return list;
    }

    @Override
    public LayuiResult addContent(TbContent tbContent) {

        if(tbContent==null){
            return LayuiResult.build(500,"增加失败");
        }


        tbContent.setCreated(new Date());
        tbContent.setUpdated(new Date());
        int count = tbContentMapper.addContent(tbContent);

        if(count <= 0){
            return LayuiResult.build(500,"添加失败");
        }

        //检查缓存中是否有相关数据
        String key = jedisClient.get(tbContent.getCategoryId() + "C");
        if(key!=null&&key!=""){
            jedisClient.set(tbContent.getCategoryId()+"C",JsonUtils.objectToJson(tbContent));
            jedisClient.expire(tbContent.getContent()+"C",RandomNum.randomNum()*60*60);
        }else {

            jedisClient.del(tbContent.getCategoryId().toString());
        }
        return LayuiResult.build(200,"增加成功");



    }
}
