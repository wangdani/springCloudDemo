package com.wang.cloud.situation.es.service;

/*
 *@author: Wang He
 *@time: 2020/3/23 0023 11:21
 *@description:
 */

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "eq-city-eureka-client",path = "eq-city-eureka-client")
public interface CityClient {

    @GetMapping("/city/listCity")
    List listCity();
}