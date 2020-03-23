package com.wang.cloud.situation.es.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

/*
 *@author: Wang He
 *@time: 2020/3/23 0023 11:43
 *@description:
 */
@FeignClient(name = "eq-data-eureka-client",path = "eq-data-eureka-client")
public interface DataClient {
    @PostMapping("/data/readProperty")
    Map<String,String> readProperty();
}
