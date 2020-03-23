package com.wang.cloud.situation.es.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static final String httpUrl = "https://ncportal.esrichina.com.cn/JKZX/yq_";
    public static final String httpOther = ".json";

    public static String httpData(String httpUrl){
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //例子
    public static String httpToStr(String httpArg){ //20200117
        String url = httpUrl + httpArg + httpOther;
        String str =  HttpUtils.httpData(url);
        return str;
    }

    public static void main(String[] args) {
        System.out.println("start");
        //String str = HttpUtils.httpToStr("20200217");
        String testUrl = "http://stock.finance.sina.com.cn/fundInfo/api/openapi.php/CaihuiFundInfoService.getNav?symbol=050025&datefrom=2020-02-01&dateto=2020-03-08&page=2";
        String str = HttpUtils.httpData(testUrl);
        System.out.println(str);
        System.out.println("end");
    }


}
