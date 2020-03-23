package com.wang.cloud.situation.es.service;

import com.wang.cloud.situation.es.dto.EpidemicDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

/*
 *@author: Wang He
 *@time: 2020/3/23 0023 14:55
 *@description:
 */
@FeignClient(name = "eq-eureka-client-zuul",path = "eq-eureka-client-zuul")
public interface ZuulClient {

    @GetMapping("/city/eq-city-eureka-client/city/listCity")
    List listCity();

    @PostMapping("/data/eq-data-eureka-client/data/readProperty")
    Map<String,String> readProperty();

    @PostMapping("/api/eq-api-eureka-client/epidemic/selectByObject")
    List<EpidemicDto> selectByObject(EpidemicDto dto);


}
