package com.wang.cloud.situation.es.service.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import com.wang.cloud.situation.es.service.CityDataService;
import com.wang.cloud.situation.es.tools.XmlBuilder;
import com.wang.cloud.situation.es.vo.City;
import com.wang.cloud.situation.es.vo.CityList;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:28
 *@description:
 */
@Service
public class CityDataServiceImpl implements CityDataService {

	@Override
	public List<City> listCity() throws Exception {
		// 读取XML文件
		Resource resource = new ClassPathResource("xmls/province.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "utf-8"));
		StringBuffer buffer = new StringBuffer();
		String line = "";
		
		while ((line = br.readLine()) !=null) {
			buffer.append(line);
		}
		
		br.close();
		
		// XML转为Java对象
		CityList cityList = (CityList) XmlBuilder.xmlStrToOject(CityList.class, buffer.toString());
		return cityList.getCityList();
	}

}
