package com.wang.cloud.situation.es.service;

import com.wang.cloud.situation.es.dto.EpidemicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/*
 *@author: Wang He
 *@time: 2020/3/23 0023 11:55
 *@description:
 */
@FeignClient(name = "eq-api-eureka-client",path = "eq-api-eureka-client")
public interface ApiClient {

    @PostMapping("/epidemic/selectByObject")
    List<EpidemicDto> selectByObject(EpidemicDto dto);

}
