package com.wang.cloud.situation.es.service.impl;

/*
 *@author: Wang He
 *@time: 2020/01/11 17:29
 *@description:
 */


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wang.cloud.situation.es.dao.EpidemicDao;
import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.service.EpidemicService;
import com.wang.cloud.situation.es.tools.HttpUtils;
import com.wang.cloud.situation.es.tools.MyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EpidemicServiceImpl implements EpidemicService {

    @Autowired
    private EpidemicDao dao;

    @Override
    public List<EpidemicDto> selectByObject(EpidemicDto dto) {
        return dao.selectByObject(dto);
    }

    @Override
    @Transactional
    public int insertByObject(EpidemicDto dto) {
        return dao.insertByObject(dto);
    }

    @Override
    public int deleteByObject(EpidemicDto dto) {
        return dao.deleteByObject(dto);
    }

    @Override
    @Transactional
    public int insertAll(String httpArg) {
        System.out.println("now is:"+httpArg);
        String str = HttpUtils.httpToStr(httpArg); //得到疫情json
        if(str==null){
            return -1;
        }
        Gson gson = new Gson();
        Map<String,Object> map = gson.fromJson(str,new TypeToken<HashMap<String,Object>>(){}.getType());
        List<Map<String,Object>> features = (List<Map<String, Object>>) map.get("features");
        Date date = MyUtils.strToDate(httpArg,"yyyyMMdd");
        //1.删除当天的数据
        EpidemicDto d = new EpidemicDto();
        d.setYqDate(date);
        dao.deleteByObject(d);
        String note = "添加时间为："+MyUtils.dateToStr(new Date(),"yyyy-MM-dd HH:mm:ss");
        //2.更新数据
        for(Map<String,Object> f:features){
            Map<String,Object> p = (Map<String, Object>) f.get("properties");
            EpidemicDto dto = new EpidemicDto();
            dto.setId(httpArg+MyUtils.getUUID32()); //日期加uuid
            dto.setYqDate(date);
            dto.setProvinceCode(""+MyUtils.doubleToInt((Double)p.get("adcode")));
            dto.setProvinceName((String) p.get("name"));
            dto.setXzys(MyUtils.doubleToInt((Double)p.get("新增疑似")) );
            dto.setLjys(MyUtils.doubleToInt((Double)p.get("累计疑似")) );
            dto.setXzqz(MyUtils.doubleToInt((Double)p.get("新增确诊")) );
            dto.setLjqz(MyUtils.doubleToInt((Double)p.get("累计确诊")) );
            dto.setXzsw(MyUtils.doubleToInt((Double)p.get("新增死亡")) );
            dto.setLjsw(MyUtils.doubleToInt((Double) p.get("累计死亡")) );
            dto.setNote(note);
            dao.insertByObject(dto);
        }
        return features.size();
    }




}
