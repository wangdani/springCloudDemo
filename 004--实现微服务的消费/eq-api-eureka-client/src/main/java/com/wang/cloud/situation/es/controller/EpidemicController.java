package com.wang.cloud.situation.es.controller;

import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.service.EpidemicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
/*
 *@author: Wang He
 *@time: 2020/01/11 15:32
 *@description:
 */
@RestController
@RequestMapping("/epidemic")
@Slf4j
public class EpidemicController {

    @Autowired
    private EpidemicService epidemicService;

    @PostMapping(value = "selectByObject")
    public List<EpidemicDto> selectByObject(EpidemicDto dto){
        System.out.println(dto.getProvinceName());
        List<EpidemicDto> list = epidemicService.selectByObject(dto);
        log.info("查看IP为:"+dto.getNote());
        return list;
    }

}
