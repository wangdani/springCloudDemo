package com.wang.cloud.situation.es.service;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:27
 *@description:
 */

import com.wang.cloud.situation.es.dto.EpidemicDto;

import java.util.List;

public interface EpidemicService {
    public List<EpidemicDto> selectByObject(EpidemicDto dto);
    public int insertAll(String date);
}
