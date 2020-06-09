package com.snowcat.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.snowcat.mapper.TbItemMapper;
import com.snowcat.pojo.*;
import com.snowcat.service.ItemService;
import com.snowcat.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    private TbItemMapper tbItemMapper;
    @Override
    public TbItem findTbItemById(Long itemId) {

        TbItem tbItem = tbItemMapper.findTbItemById(itemId);
        return tbItem;
    }

    @Override
    public LayuiResult findTbItemByPage(int page, int limit) {
        LayuiResult layuiResult = new LayuiResult();
        layuiResult.setCode(0);
        layuiResult.setMsg("");
        int count = tbItemMapper.findTbItemByCount();
        layuiResult.setCount(count);
        List<TbItem> data = tbItemMapper.findTbItemByPage((page-1)*limit,limit);
        layuiResult.setData(data);
        return layuiResult;

    }

    @Override
    public ExecuteResult itemDelete(List<TbItem> tbItem) {
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        int status = 3;
        Date date = new Date();
        int count  =  tbItemMapper.change(tbItem,status,date);
//        for(TbItem item:tbItem){
//            item.setUpdated(new Date());
//        }
        if(count!=tbItem.size()||tbItem.size()==0){
            executeResult.setStatus(400);
            executeResult.setMsg("删除失败");
        }else{
            executeResult.setMsg("删除成功");
            executeResult.setStatus(200);

        }

        return executeResult;

    }

    @Override
    public ExecuteResult commitUpper(List<TbItem> tbItem) {
        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        int status = 1;
        Date date = new Date();
        int count  =  tbItemMapper.change(tbItem,status,date);
//        for(TbItem item:tbItem){
//            item.setUpdated(new Date());
//        }
        if(count!=tbItem.size()||tbItem.size()==0){
            executeResult.setStatus(400);
            executeResult.setMsg("上架失败");
        }else{
            executeResult.setMsg("上架成功");
            executeResult.setStatus(200);

        }

        return executeResult;


    }

    @Override
    public ExecuteResult commitLower(List<TbItem> tbItem) {

        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setData(null);
        int status = 2;
//        for(TbItem item:tbItem){
//            item.setUpdated(new Date());
//        }
        Date date = new Date();
        int count  =  tbItemMapper.change(tbItem,status,date);

        if(count!=tbItem.size()||tbItem.size()==0){
            executeResult.setStatus(400);
            executeResult.setMsg("下架失败");
        }else{
            executeResult.setMsg("下架成功");
            executeResult.setStatus(200);

        }

        return executeResult;

    }

    @Override
    public LayuiResult searchItem(Integer page,Integer limit,String title , Long priceMin,Long priceMax,Long cid) {
        LayuiResult layuiResult = new LayuiResult();
        if (priceMin == null) {
            priceMin = 0L;
        }

        List<TbItem> list = tbItemMapper.searchItem((page-1)*limit,limit,title, priceMin, priceMax, cid);
        int count = tbItemMapper.searchItemByCount(title, priceMin, priceMax, cid);

        if (list != null) {
            int size = list.size();
            if (size <= 0) {
                layuiResult.setCode(0);
                layuiResult.setMsg("查询不到商品");
                layuiResult.setCount(0);
                return layuiResult;
            }


            layuiResult.setData(list);


            layuiResult.setCode(0);
            layuiResult.setMsg("查询成功");
            layuiResult.setCount(count);
        }




        return layuiResult;
    }

    @Override
    public ExecuteResult fileUpload(String name,byte[] bytes) {
        String fileName = IDUtils.genImageName()+name.substring(name.lastIndexOf("."));
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream("D:\\aliyun.txt");
            properties.load(fileInputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String endPoint = properties.getProperty("endPoint");
        String accessKeyId = properties.getProperty("accessKeyId");
        String accessKeySecret = properties.getProperty("accessKeySecret");
        String bucketName = properties.getProperty("bucketName");
        String objectName = properties.getProperty("objectName");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);

        OSS client = new OSSClientBuilder().build(endPoint, accessKeyId, accessKeySecret);
        client.putObject(bucketName,objectName+fileName,byteArrayInputStream);

        ExecuteResult executeResult = new ExecuteResult();
        executeResult.setStatus(200);
        executeResult.setMsg("");
        PicUrl picUrl = new PicUrl();
        picUrl.setSrc("https://"+bucketName+".oss-cn-chengdu.aliyuncs.com/"+objectName+fileName);
        executeResult.setData(picUrl);

        return executeResult;



    }

    @Override
    public ExecuteResult addItem(TbItem tbItem) {
        tbItem.setCreated(new Date());
        tbItem.setUpdated(new Date());
        tbItem.setStatus((byte)1);
        long itemId = IDUtils.genItemId();
        tbItem.setId(itemId);
        int count = tbItemMapper.addItem(tbItem);

        if(count<=0){
            return ExecuteResult.build(400,"添加失败");
        }
        return ExecuteResult.build(200,"添加成功");



    }


}
