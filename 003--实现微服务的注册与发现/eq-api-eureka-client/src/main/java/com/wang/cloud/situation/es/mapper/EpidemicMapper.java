package com.wang.cloud.situation.es.mapper;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:28
 *@description:
 */

import com.wang.cloud.situation.es.dto.EpidemicDto;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface EpidemicMapper {

    @Select("<script>" +
            "select * from epidemic_situation" +
            " where 1=1 " +
            "<if test=\"id !=null \"> and id=#{id , jdbcType=VARCHAR} </if> " +
            "<if test=\"provinceCode !=null \"> and province_code=#{provinceCode , jdbcType=VARCHAR} </if> " +
            "<if test=\"provinceName !=null \"> and province_name=#{provinceName , jdbcType=VARCHAR} </if> " +
            "<if test=\"start !=null \"> and yq_date between #{start , jdbcType=VARCHAR} and #{end , jdbcType=VARCHAR} </if> " +
            " ORDER BY yq_date "+
            "</script> " )
    @Results({
            @Result(column = "id",property = "id"),
            @Result(column = "province_code",property = "provinceCode"),
            @Result(column = "province_name",property = "provinceName"),
            @Result(column = "yq_date",property = "yqDate"),
            @Result(column = "xzys",property = "xzys"),
            @Result(column = "ljys",property = "ljys"),
            @Result(column = "xzqz",property = "xzqz"),
            @Result(column = "ljqz",property = "ljqz"),
            @Result(column = "xzsw",property = "xzsw"),
            @Result(column = "ljsw",property = "ljsw"),
            @Result(column = "note",property = "note")
    })
    public List<EpidemicDto> selectByObject(EpidemicDto dto);


}
