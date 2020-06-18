package com.snowcat.sso.service.impl;

import com.snowcat.mapper.TbUserMapper;
import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.TbUser;
import com.snowcat.sso.service.CheckService;
import com.snowcat.sso.service.JedisClient;
import com.snowcat.utils.IDUtils;
import com.snowcat.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;

import java.awt.dnd.DropTarget;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClient jedisClient;

    @Override
    public ExecuteResult checkData(String param, Integer type) {
        ExecuteResult executeResult = new ExecuteResult();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        if(type==1){
             count1 = tbUserMapper.checkName(param);
        }else if(type==2){
            count2 = tbUserMapper.checkPhone(param);
        }else if(type==3){
             count3 = tbUserMapper.checkEmail(param);
        }else{
            executeResult.setStatus(400);
            executeResult.setMsg("失败");
            executeResult.setData(false);
            return executeResult;
        }

        if(count1>0||count2>0||count3>0){
            executeResult.setStatus(400);
            executeResult.setMsg("失败");
            executeResult.setData(false);
        }else {
            executeResult.setStatus(200);
            executeResult.setMsg("成功");
            executeResult.setData(true);

        }
        return executeResult;


    }

    @Override
    public ExecuteResult register(TbUser tbUser) {
        ExecuteResult executeResult = new ExecuteResult();
        if(StringUtils.isBlank(tbUser.getUserName())){
            return ExecuteResult.build(400,"用户名不能为空");
        }
        if(StringUtils.isBlank(tbUser.getPassWord())){
            return ExecuteResult.build(400,"密码不能为空");
        }
        ExecuteResult checkData = checkData(tbUser.getUserName(), 1);
        if(!(boolean)checkData.getData()){
            return ExecuteResult.build(400,"用户名已被注册");
        }

        ExecuteResult checkData2 = checkData(tbUser.getPhone(), 2);
        if(!(boolean) checkData2.getData()){
            return ExecuteResult.build(400,"电话已被注册");
        }

        ExecuteResult checkData3 = checkData(tbUser.getEmail(), 3);
        if(!(boolean)checkData3.getData()){
            return ExecuteResult.build(400,"邮箱已被注册");
        }

        MessageDigest md5 = null;

        try {
            md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = tbUser.getPassWord().getBytes("UTF-8");
            byte[] digest = md5.digest(bytes);
//            tbUser.setPassWord(digest.toString());
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(digest);
            tbUser.setPassWord(encode);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        Date date = new Date();

        tbUser.setCreated(date);
        tbUser.setUpdated(date);
        tbUser.setIsLog(0);
        int flag = tbUserMapper.register(tbUser);

        if(flag<=0){
            return ExecuteResult.build(400,"注册失败");
        }

        return ExecuteResult.build(200,"注册成功");


    }

    @Override
    public ExecuteResult login(String userName, String passWord) {
        if(StringUtils.isBlank(userName)||StringUtils.isEmpty(userName)){
            return ExecuteResult.build(400,"用户名不能为空");

        }

        if(StringUtils.isBlank(passWord)||StringUtils.isEmpty(passWord)){
            return ExecuteResult.build(400,"密码不能为空");
        }

        TbUser user = tbUserMapper.getLogByName(userName);
        String userPassWord = user.getPassWord();

        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bytes = passWord.getBytes("UTF-8");
            byte[] digest = md5.digest(bytes);
//            String pw = digest.toString();
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(digest);

            if(!encode.equals(userPassWord)){
                return ExecuteResult.build(400,"登陆失败");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }



//        更改登录状态
        if(user.getIsLog()==0){
            tbUserMapper.updateLog(userName);
        }




//        缓存中存入TOKEN
        String token = UUID.randomUUID().toString();

        jedisClient.set(token, JsonUtils.objectToJson(user));
        jedisClient.expire(token,360000);








        return ExecuteResult.build(200,"登陆成功",token);



    }

    @Override
    public ExecuteResult checkToken(String token) {

        String tk = jedisClient.get(token);

        TbUser user = JsonUtils.jsonToPojo(tk, TbUser.class);
        if(StringUtils.isBlank(tk)||StringUtils.isEmpty(tk)){
            return ExecuteResult.build(400,"失败");
        }
        return ExecuteResult.build(200,"ok",user);

    }


}
