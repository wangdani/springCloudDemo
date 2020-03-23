package com.wang.cloud.situation.es.tools;

/*
 *@author: Wang He
 *@time: 2020/2/13 0013 9:20
 *@description:
 */

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.*;

public class PropertyUtils {

    public static void readPro(String fileName){   //"a.properties"
        Properties prop = new Properties();
        try{
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream (new FileInputStream(fileName));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                System.out.println(key+":"+prop.getProperty(key));
            }
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static Map<String,String> readToMap(String fileName){
        Map<String,String> map = new HashMap<String,String>();
        Properties prop = new Properties();
        try{
            //读取属性文件a.properties
            InputStream in = new BufferedInputStream (new FileInputStream(fileName));
            prop.load(in);     ///加载属性列表
            Iterator<String> it=prop.stringPropertyNames().iterator();
            while(it.hasNext()){
                String key=it.next();
                map.put(key,prop.getProperty(key));
            }
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return map;
    }


    public static void savePro(String fileName, Map<String,Object> map){
        Properties prop = new Properties();
        try{
            ///保存属性到b.properties文件
            FileOutputStream oFile = new FileOutputStream(fileName, true);//true表示追加打开
            Set<Map.Entry<String, Object>> entrySet = map.entrySet();
            Iterator<Map.Entry<String, Object>> iter = entrySet.iterator();
            while (iter.hasNext())
            {
                Map.Entry<String, Object> entry = iter.next();
                prop.setProperty(entry.getKey(), (String)entry.getValue());
            }
            prop.store(oFile, "The New properties file");
            oFile.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public static String readByKey(String fileName,String key){   //"a.properties" "lastDay"
        Properties prop = new Properties();
        String str = "";
        try{
            //读取属性文件a.properties
            //InputStream inFile = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName) ;   //new FileInputStream(fileName)
            InputStream in = new BufferedInputStream (new FileInputStream(fileName));
            prop.load(in);     ///加载属性列表
            str = prop.getProperty(key);
            in.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        return str;
    }


    public void backMap(){
        HashMap<String, Object> hm = new HashMap<String, Object>();
        hm.put("name", "wang");
        hm.put("phone", "154322145XX");
        hm.put("age", 25);

        Set<Map.Entry<String, Object>> entrySet = hm.entrySet();
        Iterator<Map.Entry<String, Object>> iter = entrySet.iterator();
        while (iter.hasNext())
        {
            Map.Entry<String, Object> entry = iter.next();
            System.out.println(entry.getKey() + "\t" + entry.getValue());
        }
    }


    public static void main(String[] args) {
        String name = "E:/wanghe/myproject/wuhanyq/src/main/resources/property/my.properties";
        PropertyUtils.readPro(name);
        Map<String, Object> map = new HashMap<String, Object>();
        //lastDay 2020-02-10
        map.put("key","li");
        map.put("phone","312235335");
        PropertyUtils.savePro(name,map);
    }
}
