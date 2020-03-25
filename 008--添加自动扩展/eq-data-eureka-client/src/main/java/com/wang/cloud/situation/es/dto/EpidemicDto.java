package com.wang.cloud.situation.es.dto;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:29
 *@description:
 */

import lombok.Data;

import java.util.Date;

@Data
public class EpidemicDto {

    private String id;
    //省份编码
    private String provinceCode;
    //省份名称
    private String provinceName;
    //日期
    private Date yqDate;
    //开始日期
    private String start;
    //结束日期
    private String end;
    //新增疑似
    private Integer xzys;
    //累计疑似
    private Integer ljys;
    //新增确诊
    private Integer xzqz;
    //累计确诊
    private Integer ljqz;
    //新增死亡
    private Integer xzsw;
    //累计死亡
    private Integer ljsw;

    private String note;
}
