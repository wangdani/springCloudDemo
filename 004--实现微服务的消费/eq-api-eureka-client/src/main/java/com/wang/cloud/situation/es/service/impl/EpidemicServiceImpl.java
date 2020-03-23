package com.wang.cloud.situation.es.service.impl;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:29
 *@description:
 */


import com.wang.cloud.situation.es.dao.EpidemicDao;
import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.service.EpidemicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@Slf4j
public class EpidemicServiceImpl implements EpidemicService {

    @Autowired
    private EpidemicDao dao;

    @Override
    public List<EpidemicDto> selectByObject(EpidemicDto dto) {
        return dao.selectByObject(dto);
    }


}
