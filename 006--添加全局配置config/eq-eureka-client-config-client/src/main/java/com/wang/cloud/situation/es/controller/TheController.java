package com.wang.cloud.situation.es.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
 *@author: Wang He
 *@time: 2020/3/24 0024 15:57
 *@description:
 */
@RestController
@RequestMapping("/the")
public class TheController {

   // @Value("${author}")
    private String author;

    @Autowired
   @Value("${cityname}")
    private String cityName;


    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @GetMapping(value = "/selectByObject")
    public String selectByObject(){
        String str = "aaa"+this.getCityName()+"bbb";
        System.out.println("this:"+str);
        return str;
    }


}
