package com.wang.cloud.situation.es.controller;

import com.wang.cloud.situation.es.tools.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;
import java.util.Map;

/*
 *@author: Wang He
 *@time: 2020/3/23 0023 11:40
 *@description:
 */
@RestController
@RequestMapping("/data")
@Slf4j
public class DataController {
    @PostMapping("/readProperty")
    public Map<String,String> readProperty() throws FileNotFoundException {
        String serverPath= ResourceUtils.getURL("classpath:property").getPath();
        //  String serverPath= "/my/wanghe/pro/whyq/property";
        log.info(serverPath);
        Map<String,String> m = PropertyUtils.readToMap(serverPath+"/my.properties");
        return m;
    }







}
