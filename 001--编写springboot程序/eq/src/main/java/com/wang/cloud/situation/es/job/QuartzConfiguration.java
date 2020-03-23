package com.wang.cloud.situation.es.job;

import com.wang.cloud.situation.es.dto.EpidemicDto;
import com.wang.cloud.situation.es.service.EpidemicService;
import com.wang.cloud.situation.es.tools.MyUtils;
import com.wang.cloud.situation.es.tools.PropertyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 *@author: Wang He
 *@time: 2020/2/13 0013 11:09
 *@description:
 */
//@Configuration
@Component
@Slf4j
public class QuartzConfiguration {
    /*
    cron:
        第一位, 表示秒, 取值是0 ~ 59
        第二位, 表示分. 取值是0 ~ 59
        第三位, 表示小时, 取值是0 ~ 23
        第四位, 表示天/日, 取值是0 ~ 31
        第五位, 表示月份, 取值是1 ~ 12
        第六位, 表示星期, 取值是1 ~ 7, 星期一，星期二...， 还有 1 表示星期日
        第七位, 年份, 可以留空, 取值是1970 ~ 2099

        0 0 3 * * ? ：每天 3 点执行；
        0 5 3 * * ?：每天 3 点 5 分执行；
        0 5 3 ? * *：每天 3 点 5 分执行，与上面作用相同；
        0 5/10 3 * * ?：每天 3 点的 5 分、15 分、25 分、35 分、45 分、55分这几个时间点执行；
        0 10 3 ? * 1：每周星期天，3 点 10 分执行，注，1 表示星期天；
        0 10 3 ? * 1#3：每个月的第三个星期，星期天执行，# 号只能出现在星期的位置。
    */

    @Autowired
    private EpidemicService epidemicService;

    /*
        更新疫情信息
        定时任务8点到14点每小时各执行一次
    */
    @Scheduled(cron = "0 0/5 8-19 * * ?")
    private void updateYqInformation() throws FileNotFoundException {
        log.info("更新疫情数据");
        String serverPath= ResourceUtils.getURL("classpath:property").getPath();
        // String serverPath= "/my/wanghe/pro/whyq/property";
        String day = MyUtils.getYesterdayByDate();  //"20200221"
        //String day = MyUtils.dateToStr(new Date(),"yyyyMMdd");  //"20200221"
        String lastDay = PropertyUtils.readByKey(serverPath+"/my.properties","lastDay");
        List<String> list = MyUtils.getDays(lastDay,day,"yyyyMMdd");
        for(String str:list){
           // if(!str.equals(day) && !str.equals(lastDay)){   //抛去首尾
                int i = epidemicService.insertAll(str);
                if(i!=-1){
                    //得到当前的确诊人数
                    EpidemicDto dto = new EpidemicDto();
                    dto.setStart(str);
                    dto.setEnd(str);
                    List<EpidemicDto> listByDay = epidemicService.selectByObject(dto);
                    BigDecimal allLjqz = new BigDecimal(0);
                    BigDecimal allLjys = new BigDecimal(0);
                    BigDecimal allLjsw = new BigDecimal(0);
                    for(EpidemicDto d:listByDay){
                        allLjqz = allLjqz.add(new BigDecimal(d.getLjqz()));
                        allLjys = allLjys.add(new BigDecimal(d.getLjys()));
                        allLjsw = allLjsw.add(new BigDecimal(d.getLjsw()));
                    }
                    log.info(allLjqz+" "+allLjsw+" "+allLjys);
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("lastDay",str);
                    map.put("allLjqz", allLjqz.toString());
                    map.put("allLjys", allLjys.toString());
                    map.put("allLjsw", allLjsw.toString());
                    PropertyUtils.savePro(serverPath+"/my.properties",map);
                }
           // }
        }

    }

    //@Scheduled(cron = "30 35 12 14 2 ?")
    private void firstTest() throws FileNotFoundException {

        epidemicService.insertAll("20200207");
        epidemicService.insertAll("20200208");
        epidemicService.insertAll("20200209");
        epidemicService.insertAll("20200210");
        epidemicService.insertAll("20200211");
        epidemicService.insertAll("20200212");
    }



}
