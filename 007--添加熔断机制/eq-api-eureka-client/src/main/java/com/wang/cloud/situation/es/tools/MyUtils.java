package com.wang.cloud.situation.es.tools;

/*
 *@author: Wang He
 *@time: 2020/2/13 0013 12:01
 *@description:
 */

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MyUtils {

    //自动生成uuid
    public static String getUUID32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    //日期转String
    public static String dateToStr(Date date, String flag){   //"yyyy-MM-dd HH:mm:ss"
        SimpleDateFormat sdf = new SimpleDateFormat(flag);
        String str = sdf.format(date);
        return str;
    }

    //String转日期
    public static Date strToDate(String str, String flag){   //"yyyy-MM-dd HH:mm:ss"
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(flag);
            Date date = sdf.parse(str);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }

    }

    //String 转int
    public static int strToInt(String str){
        int i =Integer.parseInt( str );
        return i;
    }

    //double 转int
    public static int doubleToInt(double d){
        int i = new Double(d).intValue();
        return i;
    }
    //得到昨天的日期
    public static String getYesterdayByDate(){
        //实例化当天的日期
        Date today = new Date();
        //用当天的日期减去昨天的日期
        Date yesterdayDate = new Date(today.getTime()-86400000L);
        String yesterday = new SimpleDateFormat("yyyyMMdd").format(yesterdayDate);
        return yesterday;
    }

    public static List<String> getDays(String startTime, String endTime,String flag) {  //"yyyy-MM-dd"

        // 返回的日期集合
        List<String> days = new ArrayList<String>();

        DateFormat dateFormat = new SimpleDateFormat(flag);
        try {
            Date start = dateFormat.parse(startTime);
            Date end = dateFormat.parse(endTime);

            Calendar tempStart = Calendar.getInstance();
            tempStart.setTime(start);

            Calendar tempEnd = Calendar.getInstance();
            tempEnd.setTime(end);
            tempEnd.add(Calendar.DATE, +1);// 日期加1(包含结束)
            while (tempStart.before(tempEnd)) {
                days.add(dateFormat.format(tempStart.getTime()));
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return days;
    }


    public static void main(String[] args) {
        List<String> list = MyUtils.getDays("20200119","20200318","yyyyMMdd");
        for(String s:list){
            System.out.println(s);
        }

    }


}
