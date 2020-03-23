package com.wang.cloud.situation.es.controller;



import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.service.ApiClient;
import com.wang.cloud.situation.es.service.CityClient;
import com.wang.cloud.situation.es.service.DataClient;
import com.wang.cloud.situation.es.vo.City;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public CityClient cityClient;

    @Autowired
    public DataClient dataClient;

    @Autowired
    public ApiClient apiClient;

    @PostMapping(value = "selectByObject")
    public List<EpidemicDto> selectByObject(EpidemicDto dto){
        List<EpidemicDto> list = apiClient.selectByObject(dto);
        return list;
    }



    @GetMapping("/total")
    public ModelAndView total(Model model) throws Exception {

        Map<String,String> m = dataClient.readProperty();
        model.addAttribute("allLjqz", m.get("allLjqz"));
        model.addAttribute("allLjys", m.get("allLjys"));
        model.addAttribute("allLjsw", m.get("allLjsw"));
        model.addAttribute("lastUpDay", m.get("lastDay"));

        List<City> cityList = cityClient.listCity();

        model.addAttribute("cityList", cityList);

        return new ModelAndView("yq/yq", "reportModel", model);
    }



}
