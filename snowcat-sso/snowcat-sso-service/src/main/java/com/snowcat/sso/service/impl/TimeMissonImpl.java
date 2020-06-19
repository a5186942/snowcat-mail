package com.snowcat.sso.service.impl;

import com.snowcat.mapper.TbUserMapper;
import com.snowcat.sso.service.JedisClient;
import com.snowcat.sso.service.TimeMisson;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimeMissonImpl implements TimeMisson {
    @Autowired
    private TbUserMapper tbUserMapper;

    @Autowired
    private JedisClient jedisClient;


    @Override
    public void executeMisson() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
        try {
            Date date = dateFormat.parse("2020-6-19 02:00:00 000");

            new Timer().scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {

//                    修改数据库中登入状态
                    tbUserMapper.updateAllLog();

//                    清空缓存中日活量
                    jedisClient.set("userDailyCount","0");
                    jedisClient.expire("userDailyCount",86400000);

                }
            },date,86400000);
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }
}
