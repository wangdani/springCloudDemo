package com.wang.cloud.situation.es.service;

import java.util.List;

import com.wang.cloud.situation.es.vo.City;

/*
 *@author: Wang He
 *@time: 2020/01/11 15:38
 *@description:
 */
public interface CityDataService {

	/**
	 * 获取City列表
	 * @return
	 * @throws Exception
	 */
	List<City> listCity() throws Exception;
}
