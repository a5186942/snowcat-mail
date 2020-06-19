package com.snowcat.sso.service;

import com.snowcat.pojo.ExecuteResult;
import com.snowcat.pojo.TbUser;

public interface CheckService {
    ExecuteResult checkData(String param, Integer type);

    ExecuteResult register(TbUser tbUser);


    ExecuteResult login(String userName, String passWord);


    ExecuteResult checkToken(String token);

    ExecuteResult logout(String token);
}
