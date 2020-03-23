package com.wang.cloud.situation.es.mapper;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:28
 *@description:
 */

import com.wang.cloud.situation.es.dto.EpidemicDto;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

public interface EpidemicMapper {


    @Insert("insert into epidemic_situation(id,province_code,province_name,yq_date,xzys,ljys,xzqz,ljqz,xzsw,ljsw,note)" +
            " VALUES(#{id , jdbcType=VARCHAR},#{provinceCode , jdbcType=VARCHAR},#{provinceName , jdbcType=INTEGER},#{yqDate , jdbcType=TIMESTAMP}," +
            " #{xzys , jdbcType=INTEGER},#{ljys , jdbcType=INTEGER}," +
            " #{xzqz , jdbcType=INTEGER},#{ljqz , jdbcType=INTEGER}," +
            " #{xzsw , jdbcType=INTEGER},#{ljsw , jdbcType=INTEGER}," +
            " #{note , jdbcType=VARCHAR}" +
            ")")
    public int insertByObject(EpidemicDto dto);


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

    @Delete("<script>" +
            "delete from epidemic_situation where 1=1 " +
            "<if test=\"id !=null \"> and id=#{id , jdbcType=VARCHAR} </if> " +
            "<if test=\"provinceCode !=null \"> and province_code=#{provinceCode , jdbcType=VARCHAR} </if> " +
            "<if test=\"provinceName !=null \"> and province_name=#{provinceName , jdbcType=VARCHAR} </if> " +
            "<if test=\"yqDate !=null \"> and yq_date=#{yqDate , jdbcType=VARCHAR} </if> " +
            "</script> " )
    public int deleteByObject(EpidemicDto dto);



}
