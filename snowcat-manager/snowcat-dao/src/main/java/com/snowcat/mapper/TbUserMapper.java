package com.snowcat.mapper;


import com.snowcat.pojo.TbUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface TbUserMapper {
    @Select("select count(*) from tbuser where userName=#{param}")
    Integer checkName(String param);

    @Select(("select count(*) from tbuser where phone=#{param}"))
    Integer checkPhone(String param);

    @Select("select count(*) from tbuser where email=#{param}")
    Integer checkEmail(String param);

    @Select("select * from tbuser where userName=#{userName}" )
    TbUser getLogByName(String userName);

    @Insert("INSERT INTO tbuser (userName,`passWord`,phone,email,created,updated,isLog) " +
            "values" +
            " (#{userName},#{passWord},#{phone},#{email},#{created},#{updated},#{isLog})")
    Integer register(TbUser tbUser);

    @Update("update tbuser set isLog=1 where userName=#{userName}")
    void updateLog(String userName);
}