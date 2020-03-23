package com.wang.cloud.situation.es.controller;

import com.wang.cloud.situation.es.service.CityDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *@author: Wang He
 *@time: 2020/3/19 0019 9:41
 *@description:
 */
@RestController
@RequestMapping("/city")
@Slf4j
public class CityController {
    @Autowired
    private CityDataService cityDataService;
    @PostMapping("/list")
    public List total(Model model) throws Exception {
        List list = cityDataService.listCity();
        return list;
    }


}
