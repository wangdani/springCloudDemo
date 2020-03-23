package com.wang.cloud.situation.es.controller;



import com.wang.cloud.situation.es.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @GetMapping("/total")
    public ModelAndView total(Model model) throws Exception {

        //TODO 添加三数 
        model.addAttribute("allLjqz", 59882);
        model.addAttribute("allLjys", 13435);
        model.addAttribute("allLjsw", 1368);
        model.addAttribute("lastUpDay", "20200312");

        //TODO 添加city list
        List<City> cityList = new ArrayList<City>();
        City city1 = new City();
        city1.setCityCode("120000");
        city1.setCityName("天津市");
        cityList.add(city1);

        City city2 = new City();
        city2.setCityCode("130000");
        city2.setCityName("河北省");
        cityList.add(city2);

        model.addAttribute("cityList", cityList);

        return new ModelAndView("yq/yq", "reportModel", model);
    }



}
