package com.springboot.shiro.security;

import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.*;

/**
*
* Java 加密解密之消息摘要算法
* @author jiasx
* @create 2017/10/18 12:20
**/
public class HmacSHA256Utils {

    public static void main(String[] args){
        String key = "andy123456";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("params2","gril");
        paramMap.put("username","admin");
        paramMap.put("params1","love");
        //在服务器端生成客户端参数消息摘要
        String serverDigest = HmacSHA256Utils.digest(key, paramMap);
        System.out.println(serverDigest);
        Map<String, String> remap = sortMapByKey(paramMap);
        for(Map.Entry<String,String> entry:remap.entrySet()){
            System.out.println(entry.getKey()+"========="+entry.getValue());
        }

    }


    /**
     * 使用 Map按key进行排序
     * @param map
     * @return
     */
    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<>(String::compareTo);
        sortMap.putAll(map);

//       jdk8以下的使用方式
//        Map<String, String> sortMap = new TreeMap<String, String>(new Comparator<String>(){
//            @Override
//            public int compare(String str1, String str2) {
//
//                return str1.compareTo(str2);
//            }
//        });
        return sortMap;
    }

    /**
     * 根据正文内容和密钥，生成内容摘要
     * @param key 密钥
     * @param content 正文内容
     * @return
     */
    public static String digest(String key, String content) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            byte[] secretByte = key.getBytes("utf-8");
            byte[] dataBytes = content.getBytes("utf-8");
 
            SecretKey secret = new SecretKeySpec(secretByte, "HMACSHA256");
            mac.init(secret);
 
            byte[] doFinal = mac.doFinal(dataBytes);
            byte[] hexB = new Hex().encode(doFinal);
            return new String(hexB, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据参数和密钥，生成内容摘要
     * @param key 密钥
     * @param map 参数
     * @return
     */
    public static String digest(String key, Map<String, ?> map) {
        StringBuilder s = new StringBuilder();
        for(Object values : map.values()) {
            if(values instanceof String[]) {
                for(String value : (String[])values) {
                    s.append(value);
                }
            } else if(values instanceof List) {
                for(String value : (List<String>)values) {
                    s.append(value);
                }
            } else {
                s.append(values);
            }
        }
        return digest(key, s.toString());
    }

}