package com.wang.cloud.situation.es.service;

/*
 *@author: Wang He
 *@time: 2020/3/24 0024 17:00
 *@description:
 */

import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.vo.City;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ZuulClientFallback implements ZuulClient {

    @Override
    public List listCity() {
        List<City> list = new ArrayList<City>();
        City city = new City();
        city.setCityCode("110000");
        city.setCityName("北京市");
        list.add(city);

        city = new City();
        city.setCityCode("120000");
        city.setCityName("天津市");
        list.add(city);

        city = new City();
        city.setCityCode("310000");
        city.setCityName("上海市");
        list.add(city);

        city = new City();
        city.setCityCode("500000");
        city.setCityName("重庆市");
        list.add(city);

        return list;
    }

    @Override
    public Map<String, String> readProperty() {
        Map<String,String> m = new HashMap<String,String>();
        m.put("allLjqz", "0");
        m.put("allLjys", "0");
        m.put("allLjsw", "0");
        m.put("lastDay", "20200201");
        return m;
    }

    @Override
    public List<EpidemicDto> selectByObject(EpidemicDto dto) {
        return null;
    }
}
