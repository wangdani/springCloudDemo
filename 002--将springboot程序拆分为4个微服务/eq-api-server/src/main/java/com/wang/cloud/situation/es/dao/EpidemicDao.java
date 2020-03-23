package com.wang.cloud.situation.es.dao;

import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.mapper.EpidemicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
 *@author: Wang He
 *@time: 2020/01/11 15:20
 *@description:
 */

//@Mapper
//@Component
@Repository
public class EpidemicDao {
    @Autowired
    private EpidemicMapper mapper;
    public List<EpidemicDto> selectByObject(EpidemicDto dto){
        return mapper.selectByObject(dto);
    }

}
